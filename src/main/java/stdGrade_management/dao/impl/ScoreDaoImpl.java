package stdGrade_management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stdGrade_management.dao.ScoreDao;
import stdGrade_management.dto.Score;
import stdGrade_management.dto.Student;
import stdGrade_management.dto.Subject;
import stdGrade_management.util.JdbcUtil;

public class ScoreDaoImpl implements ScoreDao {
	private static ScoreDaoImpl instance = new ScoreDaoImpl();

	public static ScoreDaoImpl getInstance() {
		return instance;
	}

	private ScoreDaoImpl() {
		
	}

	@Override
	public List<Score> selectScoreByAll() {
		String sql = "select stdNo, subjNo, stdScore from score";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				List<Score> list = new ArrayList<>();
				do {
					list.add(getScore(rs));
				} while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Score getScore(ResultSet rs) throws SQLException {
		Student stdNo = new Student(rs.getInt("stdNo"));
		Subject subjNo = new Subject(rs.getInt("subjNo"));
		int stdScore = rs.getInt("stdScore");
		
		return new Score(stdNo, subjNo, stdScore);
	}

	@Override
	public List<Score> selectScoreByNo(Score score) {
		String sql = "select stdNo, subjNo, stdScore from score where stdNo = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, score.getStdNo().getStdNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Score> list = new ArrayList<>();
					do {
						list.add(getScore(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertScore(Score score) {
		String sql = "insert into score values (?, ?, ?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, score.getStdNo().getStdNo());
			pstmt.setInt(2, score.getSubjNo().getSubjNo());
			pstmt.setInt(3, score.getStdScore());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateScore(Score score) {
		String sql = "update score "
				 + 	 "	set stdScore = ? "  
				 +	 " where stdNo = ? and subjNo = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, score.getStdScore());
			pstmt.setInt(2, score.getStdNo().getStdNo());
			pstmt.setInt(3, score.getSubjNo().getSubjNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteScore(Score score) {
		String sql = "delete from score where stdNo = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, score.getStdNo().getStdNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Score> selectScoreBysubjNo(Subject subject) {
		String sql = "select stdNo, subjNo, stdScore from score where subjNo = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, subject.getSubjNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Score> list = new ArrayList<>();
					do {
						list.add(getScore(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Score> selectStdScoreBysubjNo(Subject subject) {
		String sql = "select stdScore from score where subjNo = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, subject.getSubjNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Score> list = new ArrayList<>();
					do {
						list.add(getstdScore(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Score getstdScore(ResultSet rs) throws SQLException {
		int stdScore = rs.getInt("stdScore");
		
		return new Score(stdScore);
	}

}
