package stdGrade_management;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import stdGrade_management.service.StudentScoreViewService;
import stdGrade_management.ui.BanGradeUI;
import stdGrade_management.ui.BanGradeUI2;
import stdGrade_management.ui.StdScoreInsertUI;
import stdGrade_management.ui.TotalGradeUI;
import stdGrade_management.ui.list.StdScoreTablePanel;

@SuppressWarnings("serial")
public class StudentgradeMain extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pBtn;
	private JButton btnGradeInsert;
	private JButton btnGradeBan;
	private JButton btnGradeTotal;
	private StudentScoreViewService service;
	private StdScoreTablePanel pStdScoreList;
	private JButton btnNewButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentgradeMain frame = new StudentgradeMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentgradeMain() {
		service = new StudentScoreViewService();
		initialize();
	}
	private void initialize() {
		setTitle("성적관리 프로그램");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 547, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pBtn = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pBtn.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		contentPane.add(pBtn, BorderLayout.NORTH);
		
		btnGradeInsert = new JButton("학생성적입력");
		btnGradeInsert.addActionListener(this);
		pBtn.add(btnGradeInsert);
		
		btnGradeBan = new JButton("분반별성적확인");
		btnGradeBan.addActionListener(this);
		pBtn.add(btnGradeBan);
		
		btnGradeTotal = new JButton("전체성적확인");
		btnGradeTotal.addActionListener(this);
		pBtn.add(btnGradeTotal);
		
		btnNewButton = new JButton("임시버튼");
		btnNewButton.addActionListener(this);
		pBtn.add(btnNewButton);
		
		pStdScoreList = new StdScoreTablePanel();
		pStdScoreList.setService(service);
		pStdScoreList.loadData();
		contentPane.add(pStdScoreList, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
		if (e.getSource() == btnGradeTotal) {
			actionPerformedBtnGradeTotal(e);
		}
		if (e.getSource() == btnGradeBan) {
			actionPerformedBtnGradeBan(e);
		}
		if (e.getSource() == btnGradeInsert) {
			actionPerformedBtnGradeInsert(e);
		}
	}
	protected void actionPerformedBtnGradeInsert(ActionEvent e) {
		StdScoreInsertUI frame = new StdScoreInsertUI();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnGradeBan(ActionEvent e) {
		BanGradeUI frame = new BanGradeUI();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnGradeTotal(ActionEvent e) {
		TotalGradeUI frame = new TotalGradeUI();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		BanGradeUI2 frame = new BanGradeUI2();
		frame.setVisible(true);
	}
}
