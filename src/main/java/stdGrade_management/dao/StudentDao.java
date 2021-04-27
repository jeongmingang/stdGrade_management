package stdGrade_management.dao;

import java.util.List;

import stdGrade_management.dto.Student;

public interface StudentDao {
	List<Student> selectStudentByAll();
	
	Student selectStudentByNo(Student student); 
	
	List<Student> selectStudentByName(Student student); 
	
	int insertStudent(Student student);
	int updateStudent(Student student);
	int deleteStudent(Student student);
}
