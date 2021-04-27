package stdGrade_management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stdGrade_management.dao.StudentScoreViewDao;
import stdGrade_management.dto.Ban;
import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentScoreView;
import stdGrade_management.util.JdbcUtil;

public class StudentScoreViewDaoImpl implements StudentScoreViewDao {
	private static StudentScoreViewDaoImpl instance = new StudentScoreViewDaoImpl();
	
	public static StudentScoreViewDaoImpl getInstance() {
		if (instance == null) {
			instance = new StudentScoreViewDaoImpl();
		}
		return instance;
	}

	public StudentScoreViewDaoImpl() {
		
	}

	@Override
	public List<StudentScoreView> selectStudentScoreViewAll() {
		String sql = "select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore " 
				 +	 "	from vw_student_score";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<StudentScoreView> list = new ArrayList<>();
				do {
					list.add(getStudentScoreView(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private StudentScoreView getStudentScoreView(ResultSet rs) throws SQLException {
		Student stdNo = new Student(rs.getInt("stdNo"));
		String stdName = rs.getString("stdName");
		Ban ban = new Ban(rs.getString("ban"));
		int kor = rs.getInt("국어");
		int eng = rs.getInt("영어");
		int math = rs.getInt("수학");
		int soc = rs.getInt("사회");
		int sci = rs.getInt("과학");
		int sumScore = rs.getInt("sumScore");
		double avgScore = rs.getDouble("avgScore");
		
		return new StudentScoreView(stdNo, stdName, ban, kor, eng, math, soc, sci, sumScore, avgScore);
	}

	@Override
	public StudentScoreView selectStudentScoreByNo(Student student) {
		String sql = "select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore "
				 + 	 "	from vw_student_score "
				 + 	 " where stdNo = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, student.getStdNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return getStudentScoreView(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StudentScoreView> selectStudentScoreByAvg() {
		String sql = "select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore " 
				+  	"	from vw_student_score " 
				+ 	" order by avgScore desc";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<StudentScoreView> list = new ArrayList<>();
				do {
					list.add(getStudentScoreView(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StudentScoreView> selectStudentScoreTopByAvg(int limit) {
		String sql = "select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore "
				 + 	 "	from vw_student_score " 
				 + 	 "	order by avgScore desc limit ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, limit);
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					List<StudentScoreView> list = new ArrayList<>();
					do {
						list.add(getStudentScoreView(rs));
					} while (rs.next());
					return list;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StudentScoreView> selectStudentScoreBySubject(String sub) {
		String sql = "select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore "
				 + 	 "	from vw_student_score "
				 + 	 "	order by " + sub + " desc, avgScore desc";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<StudentScoreView> list = new ArrayList<>();
				do {
					list.add(getStudentScoreView(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StudentScoreView> selectStudentScoreTopBySubject(String sub, int limit) {
		String sql = "select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore "
				 + 	 "	from vw_student_score " 
				 + 	 " order by " + sub + " desc limit ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, limit);
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					List<StudentScoreView> list = new ArrayList<>();
					do {
						list.add(getStudentScoreView(rs));
					} while (rs.next());
					return list;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StudentScoreView> selectStudentScoreByBan(Ban ban) {
		String sql = "select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore "
				 + 	 "	from vw_student_score "
				 +	 "	where ban = ? " 
				 + 	 "	order by avgScore desc";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, ban.getBan());
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					List<StudentScoreView> list = new ArrayList<>();
					do {
						list.add(getStudentScoreView(rs));
					} while (rs.next());
					return list;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StudentScoreView> selectStudentScoreByBanSubject(Ban ban, String sub) {
		String sql = "select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore "
				 + 	 "	from vw_student_score "
				 + 	 "	where ban = ? " 
				 +   " order by " + sub + " desc, avgScore desc";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, ban.getBan());
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					List<StudentScoreView> list = new ArrayList<>();
					do {
						list.add(getStudentScoreView(rs));
					} while (rs.next());
					return list;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
