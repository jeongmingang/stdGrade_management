package stdGrade_management.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import stdGrade_management.dto.Score;
import stdGrade_management.dto.StudentScoreView;
import stdGrade_management.dto.Subject;
import stdGrade_management.service.StudentScoreViewService;
import stdGrade_management.ui.content.StdScoreTotalPanel;
import stdGrade_management.ui.content.SubjectAvgPanel;
import stdGrade_management.ui.list.StudentScoreViewTablePanel;

@SuppressWarnings("serial")
public class TotalStdScoreUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private StudentScoreViewTablePanel pList;
	private StudentScoreViewService service;
	private StdScoreTotalPanel pContent;
	private JButton btnSort;
	private JButton btnClear;
	private JLabel lblScoreView;
	private SubjectAvgPanel pAvgList;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JButton btnDispose;
	private JPanel panel_5;

	public TotalStdScoreUI() {
		service = new StudentScoreViewService();
		initialize();
	}
	
	private void initialize() {
		setTitle("전체성적확인");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 100, 878, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 10));
		setContentPane(contentPane);
		
		pList = new StudentScoreViewTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList, BorderLayout.CENTER);
		
		pAvgList = new SubjectAvgPanel();
		pAvgList.setBorder(new EmptyBorder(10, 10, 10, 10));
		pList.add(pAvgList, BorderLayout.SOUTH);
		
		JPanel pNorth = new JPanel();
		contentPane.add(pNorth, BorderLayout.NORTH);
		pNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_2 = new JPanel();
		pNorth.add(panel_2);
		
		lblScoreView = new JLabel("전체성적확인");
		panel_2.add(lblScoreView);
		
		panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		pContent = new StdScoreTotalPanel();
		pContent.setService(service);
		pNorth.add(pContent);
		
		JPanel panel = new JPanel();
		pNorth.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		
		JPanel pBtn = new JPanel();
		panel.add(pBtn);
		pBtn.setLayout(new GridLayout(0, 2, 10, 0));
		
		btnSort = new JButton("조회");
		btnSort.addActionListener(this);
		pBtn.add(btnSort);
		
		btnClear = new JButton("초기화");
		btnClear.addActionListener(this);
		pBtn.add(btnClear);
		
		panel_5 = new JPanel();
		pNorth.add(panel_5);
		
		btnDispose = new JButton("나가기");
		btnDispose.addActionListener(this);
		pNorth.add(btnDispose);

		// 국어 평균점수
		List<Score> korScoreList = service.showStdScoreBySubjNo(new Subject(1));
		int korSum = 0;
		for(int i = 0; i < korScoreList.size(); i++) {
			korSum += korScoreList.get(i).getStdScore();
		}
		pAvgList.getTfKor().setText("      국어 : " + korSum/korScoreList.size());
		
		//영어 평균점수
		List<Score> engScoreList = service.showStdScoreBySubjNo(new Subject(2));
		int engSum = 0;
		for(int i = 0; i < engScoreList.size(); i++) {
			engSum += engScoreList.get(i).getStdScore();
		} 
		pAvgList.getTfEng().setText("      영어 : " + engSum/engScoreList.size());
		
		//수학 평균점수
		List<Score> mathScoreList = service.showStdScoreBySubjNo(new Subject(3));
		int mathSum = 0;
		for(int i = 0; i < mathScoreList.size(); i++) {
			mathSum += mathScoreList.get(i).getStdScore();
		}
		pAvgList.getTfMath().setText("      수학 : " + mathSum/mathScoreList.size());
		
		//사회 평균점수
		List<Score> socScoreList = service.showStdScoreBySubjNo(new Subject(4));
		int socSum = 0;
		for(int i = 0; i < socScoreList.size(); i++) {
			socSum += socScoreList.get(i).getStdScore();
		}
		pAvgList.getTfSoc().setText("      사회 : " + socSum/socScoreList.size());
		
		//과학 평균점수
		List<Score> sciScoreList = service.showStdScoreBySubjNo(new Subject(5));
		int sciSum = 0;
		for(int i = 0; i < sciScoreList.size(); i++) {
			sciSum += sciScoreList.get(i).getStdScore();
		}
		pAvgList.getTfSci().setText("      과학 : " + sciSum/sciScoreList.size());
		
		//전체 과목 평균점수
		List<Subject> subjList = service.showSubjectList();
		List<StudentScoreView> stdViewList = service.showStdScores();
	
		int subjSum = korSum + engSum + mathSum + socSum + sciSum;
		for(int i = 0; i<subjList.size(); i++) {
			sciSum += subjList.get(i).getSubjNo();
		}
		pAvgList.getTfAvg().setText("  과목평균 : " + subjSum/subjList.size()/stdViewList.size());
	}	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDispose) {
			actionPerformedBtnDispose(e);
		}
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		}
		if (e.getSource() == btnSort) {
			actionPerformedBtnSort(e);
		}
	}
	
	protected void actionPerformedBtnSort(ActionEvent e) {
		int limit = pContent.getLimit();
		String sub = pContent.getSubject().getSubjName();
		
		if (limit != 0 && sub != null) {
			List<StudentScoreView> stdList = service.showStdScoreTopBySub(sub, limit);
			pList.setInitList(stdList);
			pList.setList();
		} else if (limit == 0 && sub != null) {
			List<StudentScoreView> stdList = service.showStdScoreBySub(sub);
			pList.setInitList(stdList);
			pList.setList();
		} else if (limit != 0 && sub == null) {
			List<StudentScoreView> stdList = service.showStdScoreTopByAvg(limit);
			pList.setInitList(stdList);
		} else {
			List<StudentScoreView> stdList = service.showStdScoreByAvg();
			pList.setInitList(stdList);
		}
		
		pList.setList();
	}
	
	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTf();
		pList.initList();
		pList.loadData();
	}
	
	protected void actionPerformedBtnDispose(ActionEvent e) {
		dispose();
	}
}
