package stdGrade_management.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentDetail;
import stdGrade_management.service.StudentDetailService;
import stdGrade_management.ui.content.StdDetailManagerPanel;
import stdGrade_management.ui.list.StudentTablePanel;

@SuppressWarnings("serial")
public class StdDetailManagerUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private StdDetailManagerPanel pContent;
	private JButton btnAdd;
	private JButton btnCancel;
	private JButton btnDispose;
	StudentDetailService service;
	private JPanel panel_2;
	private JPanel pNorth;
	private JLabel lblTitle;
	private StudentTablePanel pStdTable;
	private JPanel panel;
	private JButton btnClear;
	private JPanel panel_1;

	public void setpStdTable(StudentTablePanel pStdTable) {
		this.pStdTable = pStdTable;
	}
	
	public StdDetailManagerUI(boolean isBtns) {
		service = new StudentDetailService();
		initialize(isBtns);
	}
	
	private void initialize(boolean isBtns) {
		setTitle("학생 상세정보");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 487, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pContent = new StdDetailManagerPanel();
		pContent.setTfStdNo(new Student(20001));
		contentPane.add(pContent);
		pContent.setLayout(new BoxLayout(pContent, BoxLayout.Y_AXIS));
		
		JPanel pBtns = new JPanel();
		pBtns.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(pBtns, BorderLayout.SOUTH);
		
		btnAdd = new JButton("");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCancel = new JButton("");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		btnClear = new JButton("초기화");
		btnClear.addActionListener(this);
		
		panel_1 = new JPanel();
		pBtns.add(panel_1);
		pBtns.add(btnClear);
		
		panel = new JPanel();
		pBtns.add(panel);
		
		panel_2 = new JPanel();
		pBtns.add(panel_2);
		
		btnDispose = new JButton("나가기");
		btnDispose.addActionListener(this);
		pBtns.add(btnDispose);
		
		pNorth = new JPanel();
		contentPane.add(pNorth, BorderLayout.NORTH);
		
		lblTitle = new JLabel("학생 상세정보 화면");
		lblTitle.setFont(new Font("함초롬돋움", Font.BOLD, 20));
		pNorth.add(lblTitle);
		
		if (isBtns) {
			btnAdd.setText("추가");
			btnCancel.setText("삭제");
		}else {
			btnAdd.setText("수정");
			btnCancel.setText("삭제");
		}
	}
	
	public void setStdNo(Student stdNo) {
		pContent.setTfStdNo(stdNo);
	}
	
	public void setDetailItems(StudentDetail stdDetail) {
		pContent.setItem(stdDetail);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		}
		try {
			if (e.getActionCommand().equals("취소")) {
				actionPerformedBtnCancel(e);
			}
			if (e.getActionCommand().equals("추가")) {
				actionPerformedBtnAdd(e);
			}
			if (e.getActionCommand().equals("수정")) {
				actionPerformedBtnUpdate(e);
			}
			if (e.getActionCommand().equals("삭제")) {
				actionPerformedBtnDelete(e);
			}
			if (e.getActionCommand().equals("나가기")) {
				actionPerformedBtnDispose(e);
			}
		}
		catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "공백이 존재합니다.", "경고 메세지", JOptionPane.WARNING_MESSAGE, null);
		}
	}
	protected void actionPerformedBtnDispose(ActionEvent e) {
		dispose();
	}
	
	protected void actionPerformedBtnDelete(ActionEvent e) {
		StudentDetail deleteStdDetail = pContent.getItem();
		service.removeStdDetail(new Student(deleteStdDetail.getStdNo()));
		
		JOptionPane.showMessageDialog(null, "상세 정보 삭제완료");
		pContent.clearTf();
		pStdTable.loadData();
		dispose();
	}
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		StudentDetail updateDetail = pContent.getItem();
		service.modifyStdDetail(updateDetail);
		JOptionPane.showMessageDialog(null, "상세 정보 수정 완료");
		pContent.clearTf();
		dispose();
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		StudentDetail newStdDetail = pContent.getItem();
		service.addStdDetail(newStdDetail);
		
		JOptionPane.showMessageDialog(null, "상세 정보 추가완료");
		pContent.clearTf();
		pStdTable.loadData();
		dispose();
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pContent.clearTf();
		if (btnAdd.getText().equals("수정")) {
			btnAdd.setText("추가");
		}
	}

	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTf();
	}
}
