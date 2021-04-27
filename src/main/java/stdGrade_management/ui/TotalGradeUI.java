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

import stdGrade_management.dto.StudentScoreView;
import stdGrade_management.service.StudentScoreViewService;
import stdGrade_management.ui.content.StdScoreTotalPanel;
import stdGrade_management.ui.list.StudentScoreViewTablePanel;

@SuppressWarnings("serial")
public class TotalGradeUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private StudentScoreViewTablePanel pList;
	private StudentScoreViewService service;
	private StdScoreTotalPanel pContent;
	private JButton btnSort;
	private JButton btnClear;
	private JLabel lblScoreView;

	public TotalGradeUI() {
		service = new StudentScoreViewService();
		initialize();
	}
	
	private void initialize() {
		setTitle("전체성적확인");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 100, 802, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 10));
		setContentPane(contentPane);
		
		pList = new StudentScoreViewTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList, BorderLayout.CENTER);
		
		JPanel pNorth = new JPanel();
		contentPane.add(pNorth, BorderLayout.NORTH);
		pNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblScoreView = new JLabel("전체성적확인");
		pNorth.add(lblScoreView);
		
		pContent = new StdScoreTotalPanel();
		pContent.setService(service);
		pNorth.add(pContent);
		
		JPanel panel = new JPanel();
		pNorth.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel pBtn = new JPanel();
		panel.add(pBtn);
		pBtn.setLayout(new GridLayout(0, 2, 10, 0));
		
		btnSort = new JButton("조회");
		btnSort.addActionListener(this);
		pBtn.add(btnSort);
		
		btnClear = new JButton("초기화");
		btnClear.addActionListener(this);
		pBtn.add(btnClear);
	}	

	public void actionPerformed(ActionEvent e) {
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
		} else if (limit == 0 && sub == null) {
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
}
