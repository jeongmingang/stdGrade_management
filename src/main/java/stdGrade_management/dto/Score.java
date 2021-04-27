package stdGrade_management.dto;

public class Score {
	private Student stdNo;
	private Subject subjNo;
	private int stdScore;
	
	public Score() {
		
	}
	
	public Score(Student stdNo) {
		this.stdNo = stdNo;
	}

	public Score(Student stdNo, Subject subjNo, int stdScore) {
		this.stdNo = stdNo;
		this.subjNo = subjNo;
		this.stdScore = stdScore;
	}
	
	public Student getStdNo() {
		return stdNo;
	}

	public void setStdNo(Student stdNo) {
		this.stdNo = stdNo;
	}

	public Subject getSubjNo() {
		return subjNo;
	}

	public void setSubjNo(Subject subjNo) {
		this.subjNo = subjNo;
	}

	public int getStdScore() {
		return stdScore;
	}

	public void setStdScore(int stdScore) {
		this.stdScore = stdScore;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s", stdNo, subjNo, stdScore);
	}
	
	
}
