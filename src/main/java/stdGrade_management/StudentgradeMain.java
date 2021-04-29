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
import stdGrade_management.ui.BanStdScoreUI;
import stdGrade_management.ui.StdManagerUI;
import stdGrade_management.ui.StdScoreInsertUI;
import stdGrade_management.ui.StdScoreManagerUI;
import stdGrade_management.ui.TotalStdScoreUI;
import stdGrade_management.ui.list.StdScoreTablePanel;

@SuppressWarnings("serial")
public class StudentgradeMain extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pBtn;
	private JButton btnStdScoreInsert;
	private JButton btnBanStdScore;
	private JButton btnTotalStdScore;
	private StudentScoreViewService service;
	private StdScoreTablePanel pStdScoreList;
	private JButton btnStdManager;
	private JButton btnStdScoreManager;

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
		setBounds(100, 100, 656, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pBtn = new JPanel();
		pBtn.setBorder(new EmptyBorder(5, 0, 5, 0));
		FlowLayout flowLayout = (FlowLayout) pBtn.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		contentPane.add(pBtn, BorderLayout.NORTH);
		
		btnStdScoreInsert = new JButton("학생성적입력");
		btnStdScoreInsert.addActionListener(this);
		pBtn.add(btnStdScoreInsert);
		
		btnStdManager = new JButton("학생정보관리");
		btnStdManager.addActionListener(this);
		pBtn.add(btnStdManager);
		
		btnStdScoreManager = new JButton("성적정보관리");
		btnStdScoreManager.addActionListener(this);
		pBtn.add(btnStdScoreManager);
		
		btnBanStdScore = new JButton("분반별성적확인");
		btnBanStdScore.addActionListener(this);
		pBtn.add(btnBanStdScore);
		
		btnTotalStdScore = new JButton("전체성적확인");
		btnTotalStdScore.addActionListener(this);
		pBtn.add(btnTotalStdScore);
		
		pStdScoreList = new StdScoreTablePanel();
		pStdScoreList.setService(service);
		pStdScoreList.loadData();
		contentPane.add(pStdScoreList, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStdScoreManager) {
			actionPerformedBtnStdScoreManager(e);
		}
		if (e.getSource() == btnStdManager) {
			actionPerformedBtnStdManager(e);
		}
		if (e.getSource() == btnTotalStdScore) {
			actionPerformedBtnTotalStdScore(e);
		}
		if (e.getSource() == btnBanStdScore) {
			actionPerformedBtnBanStdScore(e);
		}
		if (e.getSource() == btnStdScoreInsert) {
			actionPerformedBtnStdScoreInsert(e);
		}
	}
	protected void actionPerformedBtnStdScoreInsert(ActionEvent e) {
		StdScoreInsertUI frame = new StdScoreInsertUI();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnStdManager(ActionEvent e) {
		StdManagerUI frame = new StdManagerUI();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnStdScoreManager(ActionEvent e) {
		StdScoreManagerUI frame = new StdScoreManagerUI();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnBanStdScore(ActionEvent e) {
		BanStdScoreUI frame = new BanStdScoreUI();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnTotalStdScore(ActionEvent e) {
		TotalStdScoreUI frame = new TotalStdScoreUI();
		frame.setVisible(true);
	}
}
