package stdGrade_management.dao;

import java.util.List;

import stdGrade_management.dto.Ban;

public interface BanDao {
	List<Ban> selectBanByAll();
	
	int insertBan(Ban ban);
	int deleteBan(Ban ban);
}
