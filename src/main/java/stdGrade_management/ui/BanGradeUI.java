package stdGrade_management.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import stdGrade_management.dto.Ban;
import stdGrade_management.dto.StudentScoreView;
import stdGrade_management.service.StudentScoreViewService;
import stdGrade_management.ui.content.StdScoreByBanPanel;
import stdGrade_management.ui.list.StudentScoreViewTablePanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class BanGradeUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnSort;
	private JButton btnClear;
	private StudentScoreViewService service;
	private StudentScoreViewTablePanel pList;
	private StdScoreByBanPanel pContent;
	private JLabel lblBanByScore;

	public BanGradeUI() {
		service = new StudentScoreViewService();
		initialize();
	}
	private void initialize() {
		setTitle("분반별 성적");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 750, 315);
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
		
		lblBanByScore = new JLabel("분반별성적확인");
		pNorth.add(lblBanByScore);
		
		pContent = new StdScoreByBanPanel();
		pContent.setService(service);
		pNorth.add(pContent);
		
		JPanel panel_1 = new JPanel();
		pNorth.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel_1.add(panel);
		
		JPanel pBtns = new JPanel();
		panel_1.add(pBtns);
		pBtns.setLayout(new GridLayout(0, 2, 10, 0));
		
		btnSort = new JButton("정렬");
		btnSort.addActionListener(this);
		pBtns.add(btnSort);
		
		btnClear = new JButton("초기화");
		btnClear.addActionListener(this);
		pBtns.add(btnClear);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnSort) {
			actionPerformedBtnSel(e);
		}
	}
	
	protected void actionPerformedBtnSel(ActionEvent e) {
		Ban ban = pContent.getBan();
		String sub = pContent.getSubject().getSubjName();
		if (ban != null && sub != null) {
			List<StudentScoreView> stdList = service.showStdScoreByBanSub(ban, sub);
			pList.setInitList(stdList);
			pList.setList();
		} else if (ban != null && sub == null) {
			List<StudentScoreView> stdList = service.showStdScoreByBan(ban);
			pList.setInitList(stdList);
			pList.setList();
		} else if (ban == null && sub != null) {
			List<StudentScoreView> stdList = service.showStdScoreBySub(sub);
			pList.setInitList(stdList);
		} else if (ban == null && sub == null) {
			List<StudentScoreView> stdList = service.showStdScoreByAvg();
			pList.setInitList(stdList);
		}
		
		pList.setList();
	}
	
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pContent.clearTf();
		pList.initList();
		pList.loadData();
	}

}
