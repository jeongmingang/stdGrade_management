package stdGrade_management.service;

import java.util.List;

import stdGrade_management.dao.impl.StudentDaoImpl;
import stdGrade_management.dto.Student;

public class StudentService {
	private StudentDaoImpl dao = StudentDaoImpl.getInstance();
	
	public List<Student> showStudents() {
		return dao.selectStudentByAll();
	}

	public Student showStudentByNo(Student student) {
		return dao.selectStudentByNo(student);
	}
	
	public List<Student> showStudentByName(Student student) {
		return dao.selectStudentByName(student);
	}
	
	public void addStudent (Student student) {
		dao.insertStudent(student);
	}
	
	public void modifyStudent (Student student) {
		dao.updateStudent(student);
	}
	
	public void removeStudent (Student student) {
		dao.deleteStudent(student);
	}
}
