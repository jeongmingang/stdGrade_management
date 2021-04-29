package stdGrade_management.service;

import java.util.List;

import stdGrade_management.dao.impl.BanDaoImpl;
import stdGrade_management.dao.impl.ScoreDaoImpl;
import stdGrade_management.dao.impl.StudentDaoImpl;
import stdGrade_management.dao.impl.StudentScoreViewDaoImpl;
import stdGrade_management.dao.impl.SubjectDaoImpl;
import stdGrade_management.dto.Ban;
import stdGrade_management.dto.Score;
import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentScoreView;
import stdGrade_management.dto.Subject;

public class StudentScoreViewService {
	private StudentScoreViewDaoImpl stdScoreViewDao = StudentScoreViewDaoImpl.getInstance();
	private StudentDaoImpl stdDao = StudentDaoImpl.getInstance();
	private SubjectDaoImpl subjDao = SubjectDaoImpl.getInstance();
	private BanDaoImpl banDao = BanDaoImpl.getInstance();
	private ScoreDaoImpl scoreDao = ScoreDaoImpl.getInstance();	
	
	public List<Subject> showSubjectList(){
		return subjDao.selectSubjectByAll();
	}
	
	public List<Ban> showBanList(){
		return banDao.selectBanByAll();
	}
	
	public void removeStudent (Student student) {
		stdDao.deleteStudent(student);
	}
	
	public List<StudentScoreView> showStdScores(){
		return stdScoreViewDao.selectStudentScoreViewAll(); //전체 학생정보 검색
	}
	
	public StudentScoreView showStdScoreByStdNo(Student student) {
		return stdScoreViewDao.selectStudentScoreByNo(student);	//학번으로 전체 학생 정보 검색
	}
	
	public List<StudentScoreView> showStdScoreByAvg(){
		return stdScoreViewDao.selectStudentScoreByAvg(); //전체성적 평균 내림차순 정렬
	}
	
	public List<StudentScoreView> showStdScoreTopByAvg(int limit){
		return stdScoreViewDao.selectStudentScoreTopByAvg(limit); //전체성적 상위평균 인원수만큼만 가져와 내림차순 정렬
	}
	
	public List<StudentScoreView> showStdScoreBySub(String sub){
		return stdScoreViewDao.selectStudentScoreBySubject(sub); //전체성적 과목 점수 내림차순 정렬
	}
	
	public List<StudentScoreView> showStdScoreTopBySub(String sub, int limit){
		return stdScoreViewDao.selectStudentScoreTopBySubject(sub, limit); //전체성적 과목 점수 상위평균 인원수만큼만 가져와 내림차순 정렬
	}
	
	public List<StudentScoreView> showStdScoreByBan(Ban ban) {
		return stdScoreViewDao.selectStudentScoreByBan(ban); //전체성적 분반 별 조회 (평균 내림차순)
	}
	
	public List<StudentScoreView> showStdScoreByBanSub(Ban ban, String sub){
		return stdScoreViewDao.selectStudentScoreByBanSubject(ban, sub); //전체성적 분반 별 조회(과목점수 내림차순)
	}
	
	public List<Score> showScoreBySubjNo(Subject subject){
		return scoreDao.selectScoreBysubjNo(subject); //과목번호로 점수테이블 검색
	}
	
	public List<Score> showStdScoreBySubjNo(Subject subject){
		return scoreDao.selectStdScoreBysubjNo(subject); //과목번호로 학생점수 검색
	}
	
}
