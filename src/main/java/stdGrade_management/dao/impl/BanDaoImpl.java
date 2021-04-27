package stdGrade_management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stdGrade_management.dao.BanDao;
import stdGrade_management.dto.Ban;
import stdGrade_management.ui.exception.SqlConstraintException;
import stdGrade_management.util.JdbcUtil;

public class BanDaoImpl implements BanDao {
	private static final BanDaoImpl instance = new BanDaoImpl();

	public static BanDaoImpl getInstance() {
		return instance;
	}
	
	private BanDaoImpl() {
		
	}

	@Override
	public List<Ban> selectBanByAll() {
		String sql = "select ban from ban";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				List<Ban> list = new ArrayList<>();
				do {
					list.add(getBan(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Ban getBan(ResultSet rs) throws SQLException {
		String ban = rs.getString("ban");
		return new Ban(ban);
	}

	@Override
	public int insertBan(Ban ban) {
		String sql = "insert into ban values (?)";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, ban.getBan());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new SqlConstraintException();
		}
//		return 0;
	}

	@Override
	public int deleteBan(Ban ban) {
		String sql = "delete from ban where ban = ?";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, ban.getBan());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SqlConstraintException();
//			e.printStackTrace();
		}
//		return 0;
	}

}
