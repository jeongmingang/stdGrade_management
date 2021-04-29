package stdGrade_management.ui.content;

import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import stdGrade_management.dto.StudentScoreView;
import stdGrade_management.dto.Subject;
import stdGrade_management.service.StudentScoreViewService;

@SuppressWarnings("serial")
public class StdScoreTotalPanel extends JPanel {
	private JComboBox<Subject> cmbSubject;
	private StudentScoreViewService service;
	private JSpinner jsLimit;
	
	public StdScoreTotalPanel() {
		initialize();
	}
	
	public void setService(StudentScoreViewService service) {
		this.service = service;
		
		List<Subject> subjectList = service.showSubjectList();
		DefaultComboBoxModel<Subject> subjectModel = new DefaultComboBoxModel<>(new Vector<>(subjectList));
		
		List<StudentScoreView> stdViewList = service.showStdScores();
		jsLimit.setModel(new SpinnerNumberModel(0, 0, stdViewList.size(), 1));
		
		cmbSubject.setModel(subjectModel);
		cmbSubject.setSelectedIndex(-1);
	}
	
	private void initialize() {
		setLayout(new GridLayout(0, 4, 10, 10));
		
		JLabel lblLimit = new JLabel("전체인원수");
		lblLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblLimit);
		
		jsLimit = new JSpinner();
		add(jsLimit);
		
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
		int limit = (int) jsLimit.getValue();
		return limit;
	}

	public void clearTf() {
		List<StudentScoreView> stdViewList = service.showStdScores();
		jsLimit.setModel(new SpinnerNumberModel(0, 0, stdViewList.size(), 1));
		cmbSubject.setSelectedIndex(-1);
	}
}
