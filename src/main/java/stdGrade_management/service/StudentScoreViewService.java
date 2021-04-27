package stdGrade_management.service;

import java.util.List;

import stdGrade_management.dao.BanDao;
import stdGrade_management.dao.StudentScoreViewDao;
import stdGrade_management.dao.SubjectDao;
import stdGrade_management.dao.impl.BanDaoImpl;
import stdGrade_management.dao.impl.StudentScoreViewDaoImpl;
import stdGrade_management.dao.impl.SubjectDaoImpl;
import stdGrade_management.dto.Ban;
import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentScoreView;
import stdGrade_management.dto.Subject;

public class StudentScoreViewService {
	private StudentScoreViewDao stdScoreViewDao = StudentScoreViewDaoImpl.getInstance();
	private SubjectDao subjDao = SubjectDaoImpl.getInstance();
	private BanDao banDao = BanDaoImpl.getInstance();
	
	public List<Subject> showSubjectList(){
		return subjDao.selectSubjectByAll();
	}
	
	public List<Ban> showBanList(){
		return banDao.selectBanByAll();
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
	
}
