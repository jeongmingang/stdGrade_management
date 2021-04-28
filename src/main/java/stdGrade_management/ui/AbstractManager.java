package stdGrade_management.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import stdGrade_management.ui.content.AbstractContentPanel;
import stdGrade_management.ui.exception.InvalidCheckException;
import stdGrade_management.ui.exception.SqlConstraintException;
import stdGrade_management.ui.list.AbstractCustomTablePanel;

@SuppressWarnings("serial")
public abstract class AbstractManager<T> extends JFrame implements ActionListener {

	private JPanel contentPane;
	protected JButton btnAdd;
	private JButton btnClear;
	
	protected AbstractContentPanel<T> pContent;
	protected AbstractCustomTablePanel<T> pList;
	protected JMenuItem stdListByBanItem;
	
	protected static final String DETAIL_MENU = "학생상세정보 보기";
	
	
	public AbstractManager() {
		setService();	//service 연결
		initialize();
		tableLoadData(); //component load data
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = createContentPanel();
		contentPane.add(pContent);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtns.add(btnClear);
		
		pList = createTablePanel();
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}


	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(this);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(this);
		popMenu.add(deleteItem);
		
		stdListByBanItem = new JMenuItem("학생 상세보기");
		stdListByBanItem.addActionListener(this);
		popMenu.add(stdListByBanItem);
		
		return popMenu;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() instanceof JMenuItem) {
			if (e.getActionCommand().equals("삭제")) {
				actionPerformedMenuDelete();
			}
			
			if (e.getActionCommand().equals("수정")) {
				actionPerformedMenuUpdate();
			}
			
			if (e.getActionCommand().contentEquals(AbstractManager.DETAIL_MENU)){
				actionPerformedMenuGubun();
			}
	
		}else {
			if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
			}
			
			if (e.getSource() == btnAdd) {
				if (e.getActionCommand().contentEquals("추가")) {
					actionPerformedBtnAdd(e);
				}else {
					actionPerformedBtnUpdate(e);
				}
			}
		}
		}catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
//			pContent.clearTf();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	protected abstract void setService();
	protected abstract void tableLoadData();
	protected abstract AbstractContentPanel<T> createContentPanel();	
	protected abstract AbstractCustomTablePanel<T> createTablePanel();
	
	protected abstract void actionPerformedMenuGubun();
	protected abstract void actionPerformedMenuUpdate();
	protected abstract void actionPerformedMenuDelete();	
	protected abstract void actionPerformedBtnUpdate(ActionEvent e);
	protected abstract void actionPerformedBtnAdd(ActionEvent e);
	
	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTf();
		
		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
}