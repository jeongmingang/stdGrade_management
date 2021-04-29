package stdGrade_management.service;

import java.util.List;

import stdGrade_management.dao.impl.ScoreDaoImpl;
import stdGrade_management.dto.Score;

public class ScoreService {
	private ScoreDaoImpl dao = ScoreDaoImpl.getInstance();
	
	public List<Score> showScores(){
		return dao.selectScoreByAll();
	}
	
	public List<Score> showScoreByNo(Score score){
		return dao.selectScoreByNo(score);
	}
	
	public void addScore(Score score){
		dao.insertScore(score);
	}
	
	public void modifyScore(Score score) {
		dao.updateScore(score);
	}
	
	public void removeScore(Score score) {
		dao.deleteScore(score);
	}
}
