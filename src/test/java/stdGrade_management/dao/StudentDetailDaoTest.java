package stdGrade_management.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import stdGrade_management.dao.impl.StudentDetailDaoImpl;
import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentDetail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentDetailDaoTest {
	private StudentDetailDao dao = StudentDetailDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test02SelectStudentDetailByNo() {
		System.out.printf("%s()%n", "test02SelectStudentDetailByNo");
		Student student = new Student(20010);
		StudentDetail searchStdDetail = dao.selectStudentDetailByNo(student);
		Assert.assertNotNull(searchStdDetail);
		System.out.println(searchStdDetail);
	}

	@Test
	public void test01InsertStudentDetail() {
		System.out.printf("%s()%n", "test01InsertStudentDetail");
		
		StudentDetail stdDetail = new StudentDetail(20010, true, new Date(), getImage("태연.jpg"));
		
		int res = dao.insertStudentDetail(stdDetail);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectStudentDetailByNo(new Student(20010)));
	}

	private byte[] getImage(String imgName) {
		byte[] pic = null;
		File file = new File(System.getProperty("user.dir") + File.separator + "images", imgName);
		try(InputStream is = new FileInputStream(file)) {
			pic = new byte[is.available()];	//file로부터 읽은 이미지의 바이트길이로 배열 생성
			is.read(pic);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pic;
	}

	@Test
	public void test03UpdateStudentDetail() {
		System.out.printf("%s()%n", "test03UpdateStudentDetail");

		Calendar cal = GregorianCalendar.getInstance();
		cal.getTime();

		StudentDetail stdDetail = new StudentDetail(20010, false, new Date(), getImage("아이유.jpg"));
		int res = dao.updateStudentDetail(stdDetail);

		Assert.assertEquals(1, res);

		System.out.println(dao.selectStudentDetailByNo(new Student(20010)));
	}

	@Test
	public void testDeleteStudentDetail() {
		System.out.printf("%s()%n", "testDeleteStudentDetail");
		Student deleteStd = new Student(20010);
		int res = dao.deleteStudentDetail(deleteStd);
		Assert.assertEquals(1, res);
	}

}
