package stdGrade_management.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import stdGrade_management.dto.Score;
import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentScoreView;
import stdGrade_management.dto.Subject;
import stdGrade_management.service.ScoreService;
import stdGrade_management.service.StudentScoreViewService;
import stdGrade_management.service.StudentService;
import stdGrade_management.ui.content.StdInsertPanel;
import stdGrade_management.ui.content.StdScoreInsertPanel;
import stdGrade_management.ui.exception.InvalidCheckException;
import stdGrade_management.ui.exception.SqlConstraintException;
import stdGrade_management.ui.list.StudentScoreViewTablePanel;

@SuppressWarnings("serial")
public class StdScoreInsertUI extends JFrame implements ActionListener {
	
	private JPanel contentPane;
	private ScoreService scoreService;
	private StudentService stdService;
	private StudentScoreViewService viewService;	
	private StudentScoreViewTablePanel pList;
	private StdInsertPanel pWest;
	private StdScoreInsertPanel pCenter;
	private JPanel pBtn;
	private JButton btnInput;
	private JButton btnClear;
	private JPanel pNorth;
	private JLabel lblTitle;
	private JButton btnDispose;
	private JPanel panel_1;

	public StdScoreInsertUI() {
		scoreService = new ScoreService();
		stdService = new StudentService();
		viewService = new StudentScoreViewService();
		initialize();
	}
	private void initialize() {
		setTitle("학생성적입력");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(750, 100, 564, 779);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 10));
		
		pWest = new StdInsertPanel();
		pWest.setService(viewService);
		
		contentPane.add(pWest, BorderLayout.WEST);
		
		pCenter = new StdScoreInsertPanel();
		contentPane.add(pCenter, BorderLayout.CENTER);
		
		pList = new StudentScoreViewTablePanel();
		pList.setService(viewService);
		pList.loadData();
		contentPane.add(pList, BorderLayout.SOUTH);
		
		pBtn = new JPanel();
		pBtn.setBorder(new EmptyBorder(0, 0, 10, 0));
		pList.add(pBtn, BorderLayout.NORTH);
		
		btnInput = new JButton("입력");
		btnInput.addActionListener(this);
		pBtn.add(btnInput);
		
		btnClear = new JButton("삭제");
		btnClear.addActionListener(this);
		pBtn.add(btnClear);
		
		btnDispose = new JButton("나가기");
		btnDispose.addActionListener(this);
		
		panel_1 = new JPanel();
		pBtn.add(panel_1);
		pBtn.add(btnDispose);
		
		pNorth = new JPanel();
		contentPane.add(pNorth, BorderLayout.NORTH);
		
		lblTitle = new JLabel("학생 성적입력 화면");
		lblTitle.setFont(new Font("함초롬돋움", Font.BOLD, 25));
		pNorth.add(lblTitle);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDispose) {
			actionPerformedBtnDispose(e);
		}
		try {
			if (e.getSource() == btnClear) {
				actionPerformedBtnClear(e);
			}
			if (e.getSource() == btnInput) {
				actionPerformedBtnInput(e);
			}
		} catch (InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "경고 메세지", JOptionPane.WARNING_MESSAGE, null);
		} catch (SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "에러 메세지", JOptionPane.ERROR_MESSAGE, null);
			pWest.clearTf();
			pCenter.clearTf();
		}
	}
	
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Student updateStd = pWest.getStudent();
		stdService.modifyStudent(updateStd);
		pList.loadData();
		btnInput.setText("추가");
		JOptionPane.showMessageDialog(null, updateStd.getStdNo() + " 정보 수정 완료");
		pWest.clearTf();
	}
	
	protected void actionPerformedBtnInput(ActionEvent e) {
		Student newStd = pWest.getItem();
		Score score = new Score(newStd);
		
		stdService.addStudent(newStd);
		
		List<Score> list = scoreService.showScoreByNo(score);
		if (list == null) {
			StudentScoreView newScore = pCenter.getItem();
			int kor = newScore.getKor();
			int eng = newScore.getEng();
			int math = newScore.getMath();
			int soc = newScore.getSoc();
			int sci = newScore.getSci();
			
			Score korScore = new Score(newStd, new Subject(1), kor);
			scoreService.addScore(korScore);
			Score engScore = new Score(newStd, new Subject(2), eng);
			scoreService.addScore(engScore);
			Score mathScore = new Score(newStd, new Subject(3), math);
			scoreService.addScore(mathScore);
			Score socScore = new Score(newStd, new Subject(4), soc);
			scoreService.addScore(socScore);
			Score sciScore = new Score(newStd, new Subject(5), sci);
			scoreService.addScore(sciScore);
		}else {
			throw new SqlConstraintException();
		}
		
		JOptionPane.showMessageDialog(null, newStd.getStdName() + "학생을 추가했습니다.");
		
		pWest.clearTf();
		pCenter.clearTf();
		pList.loadData();
	}
	
	protected void actionPerformedBtnClear(ActionEvent e) {
		pWest.clearTf();
		pCenter.clearTf();
	}
	protected void actionPerformedBtnDispose(ActionEvent e) {
		dispose();
	}
}
