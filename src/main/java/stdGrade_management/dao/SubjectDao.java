package stdGrade_management.dao;

import java.util.List;

import stdGrade_management.dto.Subject;

public interface SubjectDao {
	List<Subject> selectSubjectByAll();
	Subject selectSubjectByNo(Subject subject);
	
	int insertSubject(Subject subject);
	int deleteSubject(Subject subject);
}
