package stdGrade_management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import stdGrade_management.dao.StudentDetailDao;
import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentDetail;
import stdGrade_management.ui.exception.SqlConstraintException;
import stdGrade_management.util.JdbcUtil;

public class StudentDetailDaoImpl implements StudentDetailDao {
	private static StudentDetailDaoImpl instance = new StudentDetailDaoImpl();
	
	public static StudentDetailDaoImpl getInstance() {
		if (instance == null) {
			instance = new StudentDetailDaoImpl();
		}
		return instance;
	}

	private StudentDetailDaoImpl() {
	}

	@Override
	public StudentDetail selectStudentDetailByNo(Student student) {
		String sql = "select stdno, pic, gender, birthday from std_detail where stdNo = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, student.getStdNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return getStudentDetail(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private StudentDetail getStudentDetail(ResultSet rs) throws SQLException {
		int stdNo = rs.getInt("stdNo");
		byte[] pic = rs.getBytes("pic");
		boolean gender = rs.getBoolean("gender");
		Date birthday = rs.getTimestamp("birthday");

		return new StudentDetail(stdNo, gender, birthday, pic);
	}

	@Override
	public int insertStudentDetail(StudentDetail stdDetail) {
		String sql = "insert into std_detail values (?, ?, ?, ?)";
		try (Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, stdDetail.getStdNo());
			pstmt.setBytes(2, stdDetail.getPic());
			pstmt.setBoolean(3, stdDetail.isGender());
			pstmt.setTimestamp(4, new Timestamp(stdDetail.getBirthday().getTime()));
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SqlConstraintException(e.getMessage(), e);
		}
	}

	@Override
	public int updateStudentDetail(StudentDetail stdDetail) {
		String sql = "update std_detail "
				 + 	 "	set pic = ?, gender = ?, birthday = ?" 
				 +	 " where stdNo = ?";
		try (Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setBytes(1, stdDetail.getPic());
			pstmt.setBoolean(2, stdDetail.isGender());
			pstmt.setTimestamp(3, new Timestamp(stdDetail.getBirthday().getTime()));
			pstmt.setInt(4, stdDetail.getStdNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteStudentDetail(Student student) {
		String sql = "delete from std_detail where stdno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, student.getStdNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}



