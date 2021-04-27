package stdGrade_management.dao;

import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentDetail;

public interface StudentDetailDao {
	StudentDetail selectStudentDetailByNo(Student student);
	
	int insertStudentDetail(StudentDetail stdDetail);
	int updateStudentDetail(StudentDetail stdDetail);
	int deleteStudentDetail(Student student);
}	
