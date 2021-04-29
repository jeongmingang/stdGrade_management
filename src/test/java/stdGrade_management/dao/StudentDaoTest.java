package stdGrade_management.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import stdGrade_management.dao.impl.StudentDaoImpl;
import stdGrade_management.dto.Ban;
import stdGrade_management.dto.Student;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentDaoTest {
	private static StudentDao dao = StudentDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectStudentByAll() {
		System.out.printf("%s()%n", "test04SelectStudentByAll");
		List<Student> list = dao.selectStudentByAll();
		Assert.assertNotNull(list);
		
		for(Student s : list) {
			System.out.println(s);
		}
	}

	@Test
	public void test05SelectStudentByNo() {
		System.out.printf("%s()%n", "test05SelectStudentByNo");
		Student student = new Student(20011);
		Student searchStd = dao.selectStudentByNo(student);
		Assert.assertNotNull(searchStd);
		System.out.println(searchStd);
	}

	@Test
	public void test06SelectStudentByName() {
		System.out.printf("%s()%n", "test06SelectStudentByName");
		Student student = new Student("이강길");
		List<Student> list = dao.selectStudentByName(student);
		Assert.assertNotNull(list);
		
		for (Student s : list) {
			System.out.println(s);
		}
	}

	@Test
	public void test01InsertStudent() {
		System.out.printf("%s()%n", "test01InsertStudent");
		Student newStd = new Student(30000, "임성준", new Ban("A02"));
		int res = dao.insertStudent(newStd);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectStudentByNo(newStd));
	}

	@Test
	public void test02UpdateStudent() {
		System.out.printf("%s()%n", "test02UpdateStudent");
		Student updateStd = new Student(30000, "신재석", new Ban("A01"));
		int res = dao.updateStudent(updateStd);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectStudentByNo(updateStd));
	}

	@Test
	public void test03DeleteStudent() {
		System.out.printf("%s()%n", "test03DeleteStudent");
		Student deleteStd = new Student(30000);
		int res = dao.deleteStudent(deleteStd);
		Assert.assertEquals(1, res);
		dao.selectStudentByAll().stream().forEach(System.out::println);
	}

}
