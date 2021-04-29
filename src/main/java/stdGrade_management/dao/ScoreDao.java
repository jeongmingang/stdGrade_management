package stdGrade_management.dao;

import java.util.List;

import stdGrade_management.dto.Score;
import stdGrade_management.dto.Subject;

public interface ScoreDao {
	List<Score> selectScoreByAll();
	List<Score> selectScoreByNo(Score score);
	
	int insertScore(Score score);
	int updateScore(Score score);
	int deleteScore(Score score);
	
	List<Score> selectScoreBysubjNo(Subject subject); //과목번호로 점수테이블 검색
	List<Score> selectStdScoreBysubjNo(Subject subject); //과목번호로 학생점수 검색
}
