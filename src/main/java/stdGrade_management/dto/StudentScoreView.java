package stdGrade_management.dto;

public class StudentScoreView {
//	select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore
	private Student stdNo;
	private String stdName;
	private Ban ban;
	private int kor;
	private int eng;
	private int math;
	private int soc;
	private int sci;
	private int sumScore;
	private double avgScore;
	
	public StudentScoreView() {
		
	}

	public StudentScoreView(Student stdNo) {
		this.stdNo = stdNo;
	}

	public StudentScoreView(int kor, int eng, int math, int soc, int sci) {
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.soc = soc;
		this.sci = sci;
	}

	public StudentScoreView(Student stdNo, int kor, int eng, int math, int soc, int sci) {
		this.stdNo = stdNo;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.soc = soc;
		this.sci = sci;
	}

	public StudentScoreView(Student stdNo, String stdName, Ban ban, int kor, int eng, int math, int soc, int sci) {
		this.stdNo = stdNo;
		this.stdName = stdName;
		this.ban = ban;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.soc = soc;
		this.sci = sci;
	}

	public StudentScoreView(Student stdNo, String stdName, Ban ban, int kor, int eng, int math, int soc, int sci,
			int sumScore, double avgScore) {
		this.stdNo = stdNo;
		this.stdName = stdName;
		this.ban = ban;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.soc = soc;
		this.sci = sci;
		this.sumScore = sumScore;
		this.avgScore = avgScore;
	}

	public Student getStdNo() {
		return stdNo;
	}

	public void setStdNo(Student stdNo) {
		this.stdNo = stdNo;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public Ban getBan() {
		return ban;
	}

	public void setBan(Ban ban) {
		this.ban = ban;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSoc() {
		return soc;
	}

	public void setSoc(int soc) {
		this.soc = soc;
	}

	public int getSci() {
		return sci;
	}

	public void setSci(int sci) {
		this.sci = sci;
	}

	public int getSumScore() {
		return sumScore;
	}

	public void setSumScore(int sumScore) {
		this.sumScore = sumScore;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stdNo == null) ? 0 : stdNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentScoreView other = (StudentScoreView) obj;
		if (stdNo == null) {
			if (other.stdNo != null)
				return false;
		} else if (!stdNo.equals(other.stdNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"stdNo=%s, stdName=%s, ban=%s, kor=%s, eng=%s, math=%s, soc=%s, sci=%s, sumScore=%s, avgScore=%s",
				stdNo, stdName, ban, kor, eng, math, soc, sci, sumScore, avgScore);
	}
	
	
}
