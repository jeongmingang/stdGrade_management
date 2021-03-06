package stdGrade_management.ui.content;

import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import stdGrade_management.dto.Ban;
import stdGrade_management.dto.Student;
import stdGrade_management.service.StudentScoreViewService;
import stdGrade_management.service.StudentService;
import stdGrade_management.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class StdManagerPanel extends AbstractContentPanel<Student> {
	private JTextField tfStdNo;
	private JTextField tfStdName;
	private JComboBox<Ban> cmbBan;
	private StudentService StdService;
	private StudentScoreViewService service;

	public StdManagerPanel() {
		initialize();
	}
	
	public void setService(StudentScoreViewService service) {
		this.service = service;
		
		List<Ban> banList = service.showBanList();
		DefaultComboBoxModel<Ban> banModel = new DefaultComboBoxModel<>(new Vector<>(banList));
		cmbBan.setModel(banModel);
		cmbBan.setSelectedIndex(-1);
	}

	private void initialize() {
		setBorder(new EmptyBorder(20, 20, 20, 50));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblStdNo = new JLabel("학번");
		lblStdNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblStdNo);
		
		tfStdNo = new JTextField();
		add(tfStdNo);
		tfStdNo.setColumns(10);
		
		JLabel lblStdName = new JLabel("이름");
		lblStdName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblStdName);
		
		tfStdName = new JTextField();
		tfStdName.setColumns(10);
		add(tfStdName);
		
		JLabel lblBan = new JLabel("분반");
		lblBan.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblBan);
		
		cmbBan = new JComboBox<>();
		add(cmbBan);
	}
	
	public Student getStudent() {
		if (tfStdNo.getText().equals("") || tfStdName.getText().equals("") 
				|| cmbBan.getSelectedIndex() == -1) {
			throw new InvalidCheckException();
		}
		
		int stdNo = Integer.parseInt(tfStdNo.getText().trim());
		String stdName = tfStdName.getText().trim();
		Ban banCode = (Ban) cmbBan.getSelectedItem();
		
		return new Student(stdNo, stdName, banCode);
	}
	
	@Override
	public void setItem(Student item) {
		tfStdNo.setText(item.getStdNo() + "");
		tfStdName.setText(item.getStdName());
		cmbBan.setSelectedItem(item.getBan());
		
		tfStdNo.setEditable(false);
	}
	@Override
	public Student getItem() {
		validCheck();
		int stdNo = Integer.parseInt(tfStdNo.getText().trim());
		String stdName = tfStdName.getText().trim();
		Ban ban = (Ban) cmbBan.getSelectedItem();
		return new Student(stdNo, stdName, ban);
	}
	@Override
	public void validCheck() {
		if (tfStdNo.getText().contentEquals("") || tfStdName.getText().equals("") 
				|| cmbBan.getSelectedIndex() == -1) {
			throw new InvalidCheckException();
		}
	}
	@Override
	public void clearTf() {
		tfStdNo.setText("");
		tfStdName.setText("");
		cmbBan.setSelectedIndex(-1);
		
		if (!tfStdNo.isEditable()) {
			tfStdNo.setEditable(true);
		}
	}

	public JTextField getTfStdNo() {
		return tfStdNo;
	}

	public void setTfStdNo(JTextField tfStdNo) {
		this.tfStdNo = tfStdNo;
	}

	public JTextField getTfStdName() {
		return tfStdName;
	}

	public void setTfStdName(JTextField tfStdName) {
		this.tfStdName = tfStdName;
	}

	public JComboBox<Ban> getCmbBan() {
		return cmbBan;
	}

	public void setCmbBan(JComboBox<Ban> cmbBan) {
		this.cmbBan = cmbBan;
	}
}
