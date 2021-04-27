package stdGrade_management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stdGrade_management.dao.SubjectDao;
import stdGrade_management.dto.Subject;
import stdGrade_management.util.JdbcUtil;

public class SubjectDaoImpl implements SubjectDao {
	private static final SubjectDaoImpl instance = new SubjectDaoImpl();

	public static SubjectDaoImpl getInstance() {
		return instance;
	}
	
	private SubjectDaoImpl() {
		
	}

	@Override
	public List<Subject> selectSubjectByAll() {
		String sql = "select subjNo, subjName from subject";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				List<Subject> list = new ArrayList<>();
				do {
					list.add(getSubject(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Subject getSubject(ResultSet rs) throws SQLException {
		int subjNo = rs.getInt("subjNo");
		String subjName = rs.getString("subjName");
		return new Subject(subjNo, subjName);
	}

	@Override
	public Subject selectSubjectByNo(Subject subject) {
		String sql = "select subjNo, subjName from subject where subjNo = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, subject.getSubjNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getSubject(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int insertSubject(Subject subject) {
		String sql = "insert into subject values (?,?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, subject.getSubjNo());
			pstmt.setString(2, subject.getSubjName());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteSubject(Subject subject) {
		String sql = "delete from subject where subjNo = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, subject.getSubjNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
