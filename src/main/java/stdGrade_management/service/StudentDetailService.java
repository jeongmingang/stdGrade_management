package stdGrade_management.service;

import stdGrade_management.dao.impl.StudentDetailDaoImpl;
import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentDetail;

public class StudentDetailService {
	private StudentDetailDaoImpl dao = StudentDetailDaoImpl.getInstance();
	
	public StudentDetail showStdDetailByNo(Student student) {
		return dao.selectStudentDetailByNo(student);
	}
	
	public void addStdDetail(StudentDetail stdDetail) {
		dao.insertStudentDetail(stdDetail);
	}
	
	public void modifyStdDetail(StudentDetail stdDetail) {
		dao.updateStudentDetail(stdDetail);
	}
	
	public void removeStdDetail(Student student) {
		dao.deleteStudentDetail(student);
	}
}
