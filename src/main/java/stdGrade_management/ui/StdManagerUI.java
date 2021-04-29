package stdGrade_management.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentDetail;
import stdGrade_management.service.StudentDetailService;
import stdGrade_management.service.StudentScoreViewService;
import stdGrade_management.service.StudentService;
import stdGrade_management.ui.content.StdManagerPanel;
import stdGrade_management.ui.exception.InvalidCheckException;
import stdGrade_management.ui.exception.NotSelectedException;
import stdGrade_management.ui.list.StudentTablePanel;

@SuppressWarnings("serial")
public class  StdManagerUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnUpdate;
	private JButton btnClear;
	private StudentService stdService;
	private StudentDetailService detailService;
	private StudentScoreViewService service;
	private StdManagerPanel pContent;
	private StudentTablePanel pList;
	private JButton btnScore;
	private JButton btnDispose;
	private JPanel panel;

	public StdManagerUI() {
		stdService = new StudentService();
		detailService = new StudentDetailService();
		service = new StudentScoreViewService();
		initialize();
	}
	
	private void initialize() {
		setTitle("학생정보관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(750, 100, 645, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pNorth = new JPanel();
		contentPane.add(pNorth, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("학생정보 관리 화면");
		lblTitle.setFont(new Font("함초롬돋움", Font.BOLD, 25));
		pNorth.add(lblTitle);
		
		JPanel pWest = new JPanel();
		pWest.setBorder(new TitledBorder(null, "학생관리", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pWest, BorderLayout.WEST);
		pWest.setLayout(new BorderLayout(0, 0));
		
		pContent = new StdManagerPanel();
		pContent.setService(service);
		pWest.add(pContent, BorderLayout.CENTER);
		
		JPanel pBtns = new JPanel();
		pWest.add(pBtns, BorderLayout.SOUTH);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(this);
		pBtns.add(btnUpdate);
		
		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtns.add(btnClear);
		
		btnScore = new JButton("성적관리");
		btnScore.addActionListener(this);
		
		panel = new JPanel();
		pBtns.add(panel);
		pBtns.add(btnScore);
		
		btnDispose = new JButton("나가기");
		btnDispose.addActionListener(this);
		pBtns.add(btnDispose);
		
		pList = new StudentTablePanel();
		pList.setService(stdService);
		pList.loadData();
		contentPane.add(pList, BorderLayout.CENTER);
		
		JPopupMenu popMenu = createPopupMenu();
		pList.setPopupMenu(popMenu);
		
		pList.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event)
			{
			  if (event.getClickCount() == 2) {
				  Student std = pList.getItem();
					StudentDetail stdDetail = detailService.showStdDetailByNo(std);
					StdDetailManagerUI frame;
					if(stdDetail == null) {
						frame = new StdDetailManagerUI(true);
					} else {
						frame = new StdDetailManagerUI(false);
						frame.setDetailItems(stdDetail);
					}
					frame.setStdNo(std);
					frame.setVisible(true);
					frame.setpStdTable(pList);
			  }
			}
		});
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem detailItem = new JMenuItem("학생 상세정보");
		detailItem.addActionListener(popupMenuListener);
		popMenu.add(detailItem);
		
		JMenuItem updateItem = new JMenuItem("학생정보 수정");
		updateItem.addActionListener(popupMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("학생 삭제");
		deleteItem.addActionListener(popupMenuListener);
		popMenu.add(deleteItem);
		
		return popMenu;
	}
	
	ActionListener popupMenuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(e.getActionCommand().equals("학생정보 수정")) {
					Student updateStd = pList.getItem();
					pContent.setItem(updateStd);
					stdService.modifyStudent(updateStd);
				}
				
				if(e.getActionCommand().equals("학생 삭제")) {
					Student delStd = pList.getItem();
					stdService.removeStudent(delStd);
					JOptionPane.showMessageDialog(null, delStd.getStdName() + "학생을 삭제했습니다.");
					pList.loadData();
				}
				if(e.getActionCommand().equals("학생 상세정보")) {
					Student std = pList.getItem();
					StudentDetail stdDetail = detailService.showStdDetailByNo(std);
					StdDetailManagerUI frame;
					if(stdDetail == null) {
						frame = new StdDetailManagerUI(true);
					} else {
						frame = new StdDetailManagerUI(false);
						frame.setDetailItems(stdDetail);
					}
					frame.setStdNo(std);
					frame.setVisible(true);
					frame.setpStdTable(pList);
				}
				
			}catch(NotSelectedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "경고 메세지", JOptionPane.WARNING_MESSAGE, null);
			}
		}
	};


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDispose) {
			actionPerformedBtnDispose(e);
		}
		if (e.getSource() == btnScore) {
			actionPerformedBtnScore(e);
		}
		try {
			if (e.getSource() == btnClear) {
				actionPerformedBtnClear(e);
			}
			if (e.getSource() == btnUpdate) {
				actionPerformedBtnUpdate(e);
			}
		}catch (InvalidCheckException | NotSelectedException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "경고 메세지", JOptionPane.WARNING_MESSAGE, null);
		}
	}
	
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Student updateStd = pContent.getStudent();
		stdService.modifyStudent(updateStd);
		JOptionPane.showMessageDialog(null, updateStd.getStdName() + "학생의 정보를 수정했습니다.");
		pList.loadData();
		pContent.clearTf();
	}
	
	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTf();
		pList.initList();
		pList.loadData();
	}
	
	protected void actionPerformedBtnScore(ActionEvent e) {
		StdScoreManagerUI frame = new StdScoreManagerUI();
		frame.setVisible(true);
		dispose();
	}
	
	protected void actionPerformedBtnDispose(ActionEvent e) {
		dispose();
	}
}
