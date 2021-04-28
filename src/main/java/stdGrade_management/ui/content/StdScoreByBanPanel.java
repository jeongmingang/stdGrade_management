package stdGrade_management.ui.content;

import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import stdGrade_management.dto.Ban;
import stdGrade_management.dto.StudentScoreView;
import stdGrade_management.dto.Subject;
import stdGrade_management.service.StudentScoreViewService;
import stdGrade_management.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class StdScoreByBanPanel extends AbstractContentPanel<StudentScoreView>{
	private JComboBox<Ban> cmbBan;
	private JComboBox<Subject> cmbSubject;
	private StudentScoreViewService service;
	
	public StdScoreByBanPanel() {
		initialize();
	}
	
	public void setService(StudentScoreViewService service) {
		this.service = service;
		
		List<Ban> banList = service.showBanList();
		DefaultComboBoxModel<Ban> banModel = new DefaultComboBoxModel<>(new Vector<>(banList));
		
		cmbBan.setModel(banModel);
		cmbBan.setSelectedIndex(-1);
		
		List<Subject> subjectList = service.showSubjectList();
		DefaultComboBoxModel<Subject> subjectModel = new DefaultComboBoxModel<>(new Vector<>(subjectList));
		
		cmbSubject.setModel(subjectModel);
		cmbSubject.setSelectedIndex(-1);
	}
	
	private void initialize() {
		setLayout(new GridLayout(0, 4, 10, 10));
		
		JLabel lblBan = new JLabel("분반");
		lblBan.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblBan);
		
		cmbBan = new JComboBox<>();
		add(cmbBan);
		
		JLabel lblSubject = new JLabel("과목명");
		lblSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSubject);
		
		cmbSubject = new JComboBox<>();
		add(cmbSubject);
	}
	
	@Override
	public void setItem(StudentScoreView item) {
	}

	@Override
	public StudentScoreView getItem() {
		return null;
	}

	public Ban getBan() {
		Ban ban = null;
		if(cmbBan.getSelectedIndex() == -1) {
			return ban;
		}
		if(cmbBan.getSelectedIndex() != -1) {
			ban = (Ban) cmbBan.getSelectedItem();
		}
		return ban;
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

	public void clearTf() {
		cmbBan.setSelectedIndex(-1);
		cmbSubject.setSelectedIndex(-1);
	}

	@Override
	public void validCheck() {
		if (cmbBan.getSelectedIndex() == -1 || cmbSubject.getSelectedIndex() == -1) {
			throw new InvalidCheckException();
		}
	}
}
