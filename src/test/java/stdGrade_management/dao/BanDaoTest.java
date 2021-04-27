package stdGrade_management.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import stdGrade_management.dao.impl.BanDaoImpl;
import stdGrade_management.dto.Ban;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BanDaoTest {
	private static BanDao dao = BanDaoImpl.getInstance();
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test03SelectBanByAll() {
		System.out.printf("%s()%n", "test03SelectBanByAll");
		List<Ban> list = dao.selectBanByAll();
		Assert.assertNotNull(list);
		for (Ban b : list) {
			System.out.println(b);
		}
	}

	@Test
	public void test01InsertBan() {
		System.out.printf("%s()%n", "test01InsertBan");
		Ban newBan = new Ban("A03");
		int res = dao.insertBan(newBan);
		Assert.assertEquals(1, res);
		System.out.println(newBan);
	}

	@Test
	public void test02DeleteBan() {
		System.out.printf("%s()%n", "test02DeleteBan");
		Ban deleteBan = new Ban("A03");
		int res = dao.deleteBan(deleteBan);
		Assert.assertEquals(1, res);
		dao.selectBanByAll().stream().forEach(System.out::println);
	}

}
