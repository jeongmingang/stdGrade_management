package stdGrade_management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stdGrade_management.dao.StudentDao;
import stdGrade_management.dto.Ban;
import stdGrade_management.dto.Student;
import stdGrade_management.util.JdbcUtil;

public class StudentDaoImpl implements StudentDao {
	private static StudentDaoImpl instance = new StudentDaoImpl();

	public static StudentDaoImpl getInstance() {
		return instance;
	}
	
	private StudentDaoImpl() {
		
	}

	public static void setInstance(StudentDaoImpl instance) {
		StudentDaoImpl.instance = instance;
	}

	@Override
	public List<Student> selectStudentByAll() {
		String sql = "select stdNo, stdName, ban from student";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Student> list = new ArrayList<>();
				do {
					list.add(getStudent(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Student getStudent(ResultSet rs) throws SQLException {
		int stdNo = rs.getInt("stdNo");
		String stdName = null;
		Ban ban = null;
		
		try {
			ban = new Ban(rs.getString("ban"));
			stdName = rs.getString("stdName");
		} catch (SQLException e) {}
		
		return new Student(stdNo, stdName, ban);
	}

	@Override
	public Student selectStudentByNo(Student student) {
		String sql = "select stdNo, stdName, ban from student where stdNo = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, student.getStdNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return getStudent(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Student> selectStudentByName(Student student) {
		String sql = "select stdNo, stdName, ban from student where stdName = ?";
		try (Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, student.getStdName());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<Student> list = new ArrayList<>();
					do {
						list.add(getStudent(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertStudent(Student student) {
		String sql = "insert into student values (?, ?, ?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, student.getStdNo());
			pstmt.setString(2, student.getStdName());
			pstmt.setString(3, student.getBan().getBan());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateStudent(Student student) {
		String sql = "update student "
				 + 	 "	set stdName = ?, ban = ? " 
				 +	 " where stdNo = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, student.getStdName());
			pstmt.setString(2, student.getBan().getBan());
			pstmt.setInt(3, student.getStdNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteStudent(Student student) {
		String sql = "delete from student where stdNo = ?";
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
