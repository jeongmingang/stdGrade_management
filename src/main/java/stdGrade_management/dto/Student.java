package stdGrade_management.dto;

public class Student {
	private int stdNo; 		
	private String stdName;	
	private Ban ban;
	
	public Student() {
		
	}

	public Student(int stdNo) {
		this.stdNo = stdNo;
	}

	public Student(String stdName) {
		this.stdName = stdName;
	}

	public Student(int stdNo, String stdName, Ban ban) {
		this.stdNo = stdNo;
		this.stdName = stdName;
		this.ban = ban;
	}

	public int getStdNo() {
		return stdNo;
	}

	public void setStdNo(int stdNo) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stdNo;
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
		Student other = (Student) obj;
		if (stdNo != other.stdNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("stdNo=%s, stdName=%s, ban=%s", stdNo, stdName, ban);
	}	
	
	
}
