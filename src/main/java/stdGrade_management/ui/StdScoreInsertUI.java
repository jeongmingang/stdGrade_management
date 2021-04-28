package stdGrade_management.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

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
import stdGrade_management.ui.list.StudentScoreViewTablePanel;

@SuppressWarnings("serial")
public class StdScoreInsertUI extends JFrame implements ActionListener {
	
	private JPanel contentPane;
	private ScoreService scoreService;
	private StudentService stdService;
	private StudentScoreViewService viewService;	
	private JButton btnInput;
	private JButton btnClear;
	private StudentScoreViewTablePanel pList;
	private StdInsertPanel pWest;
	private StdScoreInsertPanel pCenter;

	public StdScoreInsertUI() {
		scoreService = new ScoreService();
		stdService = new StudentService();
		viewService = new StudentScoreViewService();
		initialize();
	}
	private void initialize() {
		setTitle("학생성적입력");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 400, 1022, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "학생성적입력", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 10));
		
		JPanel pSouth = new JPanel();
		contentPane.add(pSouth, BorderLayout.SOUTH);
		
		btnInput = new JButton("입력");
		btnInput.addActionListener(this);
		pSouth.add(btnInput);
		
		btnClear = new JButton("삭제");
		btnClear.addActionListener(this);
		pSouth.add(btnClear);
		
		pWest = new StdInsertPanel();
		pWest.setService(viewService);
		
		contentPane.add(pWest, BorderLayout.WEST);
		
		pCenter = new StdScoreInsertPanel();
		contentPane.add(pCenter, BorderLayout.CENTER);
		
		pList = new StudentScoreViewTablePanel();
		pList.setService(viewService);
		pList.loadData();
		contentPane.add(pList, BorderLayout.EAST);
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnClear) {
				actionPerformedBtnClear(e);
			}
			if (e.getSource() == btnInput) {
				actionPerformedBtnInput(e);
			}
			if (e.getSource() == btnInput) {
				if (btnInput.getText().equals("추가")) {
					actionPerformedBtnInput(e);
				}
				if (btnInput.getText().equals("수정")) {
					actionPerformedBtnUpdate(e);
				}
			}
			} catch (InvalidCheckException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				pWest.clearTf();
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
		stdService.addStudent(newStd);
		
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
		
		JOptionPane.showMessageDialog(null, " 추가했습니다.");
		
		pWest.clearTf();
		pCenter.clearTf();
		pList.loadData();
	}
	
	protected void actionPerformedBtnClear(ActionEvent e) {
		pWest.clearTf();
		pCenter.clearTf();
	}
}
