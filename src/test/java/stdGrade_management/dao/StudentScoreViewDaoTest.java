package stdGrade_management.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import stdGrade_management.dao.impl.StudentScoreViewDaoImpl;
import stdGrade_management.dto.Ban;
import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentScoreView;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentScoreViewDaoTest {
	private static StudentScoreViewDao dao = StudentScoreViewDaoImpl.getInstance(); 
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test07SelectStudentScoreAll() {
		System.out.printf("%s()%n", "test07SelectStudentScoreAll");
		List<StudentScoreView> list = dao.selectStudentScoreViewAll();
		Assert.assertNotNull(list);
		for (StudentScoreView scv : list) {
			System.out.println(scv);
		}
	}

	@Test
	public void test08SelectStudentScoreByNo() {
		System.out.printf("%s()%n", "test08SelectStudentByNo");
		StudentScoreView stdScoreView = dao.selectStudentScoreByNo(new Student(20020));
		Assert.assertNotNull(stdScoreView);
		System.out.println(stdScoreView);
	}

	@Test
	public void test01SelectStudentScoreByAvg() {
		System.out.printf("%s()%n", "test01SelectStudentScoreByAvg");
		List<StudentScoreView> list = dao.selectStudentScoreByAvg();
		Assert.assertNotNull(list);
		for (StudentScoreView scv : list) {
			System.out.println(scv);
		}
	}

	@Test
	public void test02SelectStudentScoreTopByAvg() {
		System.out.printf("%s()%n", "test02SelectStudentScoreTopByAvg");
		int limit = 5;
		List<StudentScoreView> list = dao.selectStudentScoreTopByAvg(limit);
		Assert.assertNotNull(list);
		for (StudentScoreView scv : list) {
			System.out.println(scv);
		}
	}

	@Test
	public void test03SelectStudentScoreBySubject() {
		System.out.printf("%s()%n", "test03SelectStudentScoreBySubject");
		String subject = "국어";
		List<StudentScoreView> list = dao.selectStudentScoreBySubject(subject);
		Assert.assertNotNull(list);
		for (StudentScoreView scv : list) {
			System.out.println(scv);
		}
	}

	@Test
	public void test04SelectStudentScoreTopBySubject() {
		System.out.printf("%s()%n", "test04SelectStudentScoreTopBySubject");
		String sub = "국어";
		int limit = 5;
		List<StudentScoreView> list = dao.selectStudentScoreTopBySubject(sub, limit);
		Assert.assertNotNull(list);
		for (StudentScoreView scv : list) {
			System.out.println(scv);
		}
	}

	@Test
	public void test05SelectStudentScoreByBan() {
		System.out.printf("%s()%n", "test05SelectStudentScoreByBan");
		Ban ban = new Ban("A01");
		List<StudentScoreView> list = dao.selectStudentScoreByBan(ban);
		Assert.assertNotNull(list);
		for (StudentScoreView s : list) {
			System.out.println(s);
		}
	}

	@Test
	public void test06SelectStudentScoreByBanSubject() {
		System.out.printf("%s()%n", "test06SelectStudentScoreByBanSubject");
		Ban ban = new Ban("A01");
		String sub = "국어";
		List<StudentScoreView> list = dao.selectStudentScoreByBanSubject(ban, sub);
		Assert.assertNotNull(list);
		for (StudentScoreView s : list) {
			System.out.println(s);
		}
	}
}
