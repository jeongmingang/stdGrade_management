package stdGrade_management.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import stdGrade_management.dao.impl.ScoreDaoImpl;
import stdGrade_management.dto.Score;
import stdGrade_management.dto.Student;
import stdGrade_management.dto.Subject;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScoreDaoTest {
	private static ScoreDao dao = ScoreDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectScoreByAll() {
		System.out.printf("%s()%n", "test04SelectScoreByAll");
		List<Score> list = dao.selectScoreByAll();
		Assert.assertNotNull(list);
		for (Score s : list) {
			System.out.println(s);
		}
	}

	@Test
	public void test05SelectScoreByNo() {
		System.out.printf("%s()%n", "test05SelectScoreByNo");
		Score score = new Score(new Student(20009));
		List<Score> list = dao.selectScoreByNo(score);
		for (Score s : list) {
			System.out.println(s);
		}
	}

	@Test
	public void test01InsertScore() {
		System.out.printf("%s()%n", "test01InsertScore");
		Score newScore = new Score(new Student(30001), new Subject(2), 73);
		int res = dao.insertScore(newScore);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectScoreByNo(newScore));
	}

	@Test
	public void test02UpdateScore() {
		System.out.printf("%s()%n", "test02UpdateScore");
		Score updateScore = new Score(new Student(30001), new Subject(2), 85);
		int res = dao.updateScore(updateScore);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectScoreByNo(updateScore));
	}

	@Test
	public void test03DeleteScore() {
		System.out.printf("%s()%n", "test03DeleteScore");
		Score deleteScore = new Score(new Student(30001));
		int res = dao.deleteScore(deleteScore);
		Assert.assertEquals(1, res);
		dao.selectScoreByAll().stream().forEach(System.out::println);
	}
	
	@Test
	public void test06SelectScoreBysubjNo() {
		System.out.printf("%s()%n", "test06SelectScoreBysubNo");
		List<Score> list = dao.selectScoreBysubjNo(new Subject(2));
		Assert.assertNotNull(list);
		
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void test07SelectstdScoreBysubNo() {
		System.out.printf("%s()%n","test07SelectstdScoreBysubNo");
		List<Score> list = dao.selectStdScoreBysubjNo(new Subject(3));
		Assert.assertNotNull(list);
		
		list.stream().forEach(System.out::println);
	}
}
