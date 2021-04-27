package stdGrade_management.service;

import java.util.List;

import stdGrade_management.dao.BanDao;
import stdGrade_management.dao.impl.BanDaoImpl;
import stdGrade_management.dto.Ban;

public class BanService {
	private BanDao dao = BanDaoImpl.getInstance();
	
	public List<Ban> showBans(){
		return dao.selectBanByAll();
	}
	
	public void addBan(Ban ban) {
		dao.insertBan(ban);
	}
	
	public void removeBan(Ban ban) {
		dao.deleteBan(ban);
	}
}
