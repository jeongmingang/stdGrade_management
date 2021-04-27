package stdGrade_management.ui.content;

import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import stdGrade_management.dto.Subject;
import stdGrade_management.service.StudentScoreViewService;

@SuppressWarnings("serial")
public class StdScoreTotalPanel extends JPanel {
	private JComboBox<Subject> cmbSubject;
	private StudentScoreViewService service;
	private JTextField tfLimit;
	
	public StdScoreTotalPanel() {
		initialize();
	}
	
	public void setService(StudentScoreViewService service) {
		this.service = service;
		
		List<Subject> subjectList = service.showSubjectList();
		DefaultComboBoxModel<Subject> subjectModel = new DefaultComboBoxModel<>(new Vector<>(subjectList));
		
		cmbSubject.setModel(subjectModel);
		cmbSubject.setSelectedIndex(-1);
	}
	
	private void initialize() {
		setLayout(new GridLayout(0, 4, 10, 10));
		
		JLabel lblLimit = new JLabel("전체인원수");
		lblLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblLimit);
		
		tfLimit = new JTextField();
		add(tfLimit);
		tfLimit.setColumns(10);
		
		JLabel lblSubject = new JLabel("과목명");
		lblSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSubject);
		
		cmbSubject = new JComboBox<>();
		add(cmbSubject);
	}

	public Subject getSubject() {
		Subject subject = null;
		int SubjNo = 0;
		String SubjName = null;
		if(cmbSubject.getSelectedIndex() != -1) {
			subject = (Subject) cmbSubject.getSelectedItem();
			SubjNo = subject.getSubjNo();
			SubjName = subject.getSubjName();
		}
		return new Subject(SubjNo, SubjName);
	}
	
	public int getLimit() {
		int limit = 0;
		if (!tfLimit.getText().equals("")) {
			limit = Integer.parseInt(tfLimit.getText().trim());
		} 
		return limit;
	}

	public void clearTf() {
		tfLimit.setText("");
		cmbSubject.setSelectedIndex(-1);
	}
}
