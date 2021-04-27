package stdGrade_management.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import stdGrade_management.dao.impl.SubjectDaoImpl;
import stdGrade_management.dto.Subject;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SubjectDaoTest {
	private static SubjectDao dao = SubjectDaoImpl.getInstance();
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectSubjectByAll() {
		System.out.printf("%s()%n", "test04SelectSubjectByAll");
		List<Subject> list = dao.selectSubjectByAll();
		Assert.assertNotNull(list);
		for(Subject s : list) {
			System.out.println(s);
		}
	}

	@Test
	public void test03SelectSubjectByNo() {
		System.out.printf("%s()%n", "testSelectSubjectByNo");
		Subject subject = new Subject(3);
		Subject searchSubject = dao.selectSubjectByNo(subject);
		Assert.assertNotNull(searchSubject);
		System.out.println(searchSubject);
	}

	@Test
	public void test01InsertSubject() {
		System.out.printf("%s()%n", "testInsertSubject");
		Subject newSubject = new Subject(6, "한국사");
		int res = dao.insertSubject(newSubject);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectSubjectByNo(newSubject));
	}

	@Test
	public void test02DeleteSubject() {
		System.out.printf("%s()%n", "test02DeleteSubject");
		Subject deleteSubject = new Subject(6);
		int res = dao.deleteSubject(deleteSubject);
		Assert.assertEquals(1, res);
		dao.selectSubjectByAll().stream().forEach(System.out::println);
	}

}
