package stdGrade_management.service;

import java.util.List;

import stdGrade_management.dao.impl.SubjectDaoImpl;
import stdGrade_management.dto.Subject;

public class SubjectService {
	private SubjectDaoImpl dao = SubjectDaoImpl.getInstance();
	
	public List<Subject> showSubjects(){
		return dao.selectSubjectByAll();
	}
	
	public void showSubjectByNo(Subject subject) {
		dao.selectSubjectByNo(subject);
	}
	
	public void addSubject(Subject subject){
		dao.insertSubject(subject);
	}
	
	public void removeSubject(Subject subject) {
		dao.deleteSubject(subject);
	}
}
