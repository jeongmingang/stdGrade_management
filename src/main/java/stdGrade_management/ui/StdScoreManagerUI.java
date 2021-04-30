package stdGrade_management.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import stdGrade_management.dto.Score;
import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentScoreView;
import stdGrade_management.dto.Subject;
import stdGrade_management.service.ScoreService;
import stdGrade_management.service.StudentScoreViewService;
import stdGrade_management.service.StudentService;
import stdGrade_management.ui.content.StdManagerPanel;
import stdGrade_management.ui.content.StdScoreInsertPanel;
import stdGrade_management.ui.content.SubjectAvgPanel;
import stdGrade_management.ui.exception.InvalidCheckException;
import stdGrade_management.ui.list.StudentScoreViewTablePanel;

@SuppressWarnings("serial")
public class StdScoreManagerUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private ScoreService scoreService;
	private StudentService stdService;
	private StudentScoreViewService viewService;
	private JPanel pNorth;
	private JPanel pSouth;
	private JPanel pWest;
	private StudentScoreViewTablePanel pList;
	private StdManagerPanel pStd;
	private StdScoreInsertPanel pScore;
	private JPanel pBtns;
	private JButton btnUpdate;
	private JButton btnClear;
	private SubjectAvgPanel pAvgList;
	private JButton btnDispose;	

	public StdScoreManagerUI() {
		scoreService = new ScoreService();
		stdService = new StudentService();
		viewService = new StudentScoreViewService();
		initialize();
	}
	private void initialize() {
		setTitle("성적정보관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(750, 100, 860, 624);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pNorth = new JPanel();
		pNorth.setBorder(new EmptyBorder(10, 0, 10, 0));
		contentPane.add(pNorth, BorderLayout.NORTH);
		pNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel pTitle = new JPanel();
		pNorth.add(pTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("성적정보 관리 화면");
		pTitle.add(lblTitle);
		lblTitle.setFont(new Font("함초롬돋움", Font.BOLD, 25));
		
		pSouth = new JPanel();
		contentPane.add(pSouth, BorderLayout.SOUTH);
		pSouth.setLayout(new GridLayout(0, 1, 0, 0));
		
		pAvgList = new SubjectAvgPanel();
		pAvgList.setBorder(new EmptyBorder(10, 10, 10, 10));
		pSouth.add(pAvgList);
		pAvgList.setLayout(new GridLayout(1, 0, 10, 0));
		
		pWest = new JPanel();
		pWest.setBorder(new TitledBorder(null, "성적관리", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pWest, BorderLayout.WEST);
		pWest.setLayout(new BoxLayout(pWest, BoxLayout.Y_AXIS));
		
		pStd = new StdManagerPanel();
		pStd.setService(viewService);
		pStd.getTfStdNo().setEditable(false);
		pStd.getTfStdName().setEditable(false);
		pStd.getCmbBan().setEnabled(false);
		pWest.add(pStd);
		
		pScore = new StdScoreInsertPanel();
		pStd.setService(viewService);
		pWest.add(pScore);
		
		pBtns = new JPanel();
		pBtns.setBorder(new EmptyBorder(30, 0, 0, 0));
		pWest.add(pBtns);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(this);
		pBtns.add(btnUpdate);
		
		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtns.add(btnClear);
		
		btnDispose = new JButton("나가기");
		btnDispose.addActionListener(this);
		pBtns.add(btnDispose);
		
		pList = new StudentScoreViewTablePanel();
		pList.setService(viewService);
		pList.loadData();
		contentPane.add(pList, BorderLayout.CENTER);
		
		pList.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent  e) {
			  if (e.getClickCount() == 2) {
				  JTable table = (JTable) e.getSource();
				int row = table.getSelectedRow();
				int stdNo = (int) table.getValueAt(row, 0);
				
				Student newStd = stdService.showStudentByNo(new Student(stdNo));
				pStd.setItem(newStd);
				
				StudentScoreView score = viewService.showStdScoreByStdNo(newStd);
				pScore.setItem(score);
			  }
			}
		});
		
		// 국어 평균점수
		List<Score> korScoreList = viewService.showStdScoreBySubjNo(new Subject(1));
		int korSum = 0;
		for(int i = 0; i < korScoreList.size(); i++) {
			korSum += korScoreList.get(i).getStdScore();
		}
		pAvgList.getTfKor().setText("      국어 : " + korSum/korScoreList.size());
		
		//영어 평균점수
		List<Score> engScoreList = viewService.showStdScoreBySubjNo(new Subject(2));
		int engSum = 0;
		for(int i = 0; i< engScoreList.size(); i++) {
			engSum += engScoreList.get(i).getStdScore();
		} 
		pAvgList.getTfEng().setText("      영어 : " + engSum/engScoreList.size());
		
		//수학 평균점수
		List<Score> mathScoreList = viewService.showStdScoreBySubjNo(new Subject(3));
		int mathSum = 0;
		for(int i = 0; i< mathScoreList.size(); i++) {
			mathSum += mathScoreList.get(i).getStdScore();
		}
		pAvgList.getTfMath().setText("      수학 : " + mathSum/mathScoreList.size());
		
		//사회 평균점수
		List<Score> socScoreList = viewService.showStdScoreBySubjNo(new Subject(4));
		int socSum = 0;
		for(int i = 0; i<socScoreList.size(); i++) {
			socSum += socScoreList.get(i).getStdScore();
		}
		pAvgList.getTfSoc().setText("      사회 : " + socSum/socScoreList.size());
		
		//과학 평균점수
		List<Score> sciScoreList = viewService.showStdScoreBySubjNo(new Subject(5));
		int sciSum = 0;
		for(int i = 0; i<sciScoreList.size(); i++) {
			sciSum += sciScoreList.get(i).getStdScore();
		}
		pAvgList.getTfSci().setText("      과학 : " + sciSum/sciScoreList.size());
		
		//전체 과목 평균점수
		List<Subject> subjList = viewService.showSubjectList();
		List<StudentScoreView> stdViewList = viewService.showStdScores();
	
		int subjSum = korSum + engSum + mathSum + socSum + sciSum;
		for(int i = 0; i<subjList.size(); i++) {
			sciSum += subjList.get(i).getSubjNo();
		}
		pAvgList.getTfAvg().setText("  과목평균 : " + subjSum/subjList.size()/stdViewList.size());
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnClear) {
				actionPerformedBtnClear(e);
			}
			if (e.getSource() == btnDispose) {
				actionPerformedBtnDispose(e);
			}
			if (e.getSource() == btnUpdate) {
				actionPerformedBtnUpdate(e);
			}
		} catch (InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "경고 메시지", JOptionPane.WARNING_MESSAGE, null);
		} 
			
	}
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Student updateStd = pStd.getItem();
		
		stdService.modifyStudent(updateStd);
		
		StudentScoreView updateScore = pScore.getItem();
		int kor = updateScore.getKor();
		int eng = updateScore.getEng();
		int math = updateScore.getMath();
		int soc = updateScore.getSoc();
		int sci = updateScore.getSci();
		
		Score korScore = new Score(updateStd, new Subject(1), kor);
		scoreService.modifyScore(korScore);
		Score engScore = new Score(updateStd, new Subject(2), eng);
		scoreService.modifyScore(engScore);
		Score mathScore = new Score(updateStd, new Subject(3), math);
		scoreService.modifyScore(mathScore);
		Score socScore = new Score(updateStd, new Subject(4), soc);
		scoreService.modifyScore(socScore);
		Score sciScore = new Score(updateStd, new Subject(5), sci);
		scoreService.modifyScore(sciScore);
	
		JOptionPane.showMessageDialog(null, updateStd.getStdName() + "성적을 수정했습니다.");
		
		pStd.clearTf();
		pScore.clearTf();
		pList.loadData();
	}
	protected void actionPerformedBtnDispose(ActionEvent e) {
		dispose();
	}
	protected void actionPerformedBtnClear(ActionEvent e) {
		pStd.clearTf();
		pScore.clearTf();
	}
}
