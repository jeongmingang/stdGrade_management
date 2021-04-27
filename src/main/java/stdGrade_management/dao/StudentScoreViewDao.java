package stdGrade_management.dao;

import java.util.List;

import stdGrade_management.dto.Ban;
import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentScoreView;

public interface StudentScoreViewDao {
	List<StudentScoreView> selectStudentScoreViewAll(); //전체 학생정보 검색
	StudentScoreView selectStudentScoreByNo(Student student); //학번으로 전체 학생 정보 검색
	
	List<StudentScoreView> selectStudentScoreByAvg(); //전체성적 평균 내림차순 정렬
	List<StudentScoreView> selectStudentScoreTopByAvg(int limit); //전체성적 상위평균 인원수만큼만 가져와 내림차순 정렬
	
	List<StudentScoreView> selectStudentScoreBySubject(String sub); //전체성적 과목 점수 내림차순 정렬
	List<StudentScoreView> selectStudentScoreTopBySubject(String sub, int limit); //전체성적 과목 점수 상위평균 인원수만큼만 가져와 내림차순 정렬
	
	List<StudentScoreView> selectStudentScoreByBan(Ban ban); //전체성적 분반 별 조회 (평균 내림차순)
	List<StudentScoreView> selectStudentScoreByBanSubject(Ban ban, String sub); //전체성적 분반 별 조회(과목점수 내림차순)

}
