package stdGrade_management.dto;

public class Subject {
	private int subjNo;
	private String subjName;
	
	public Subject() {
		
	}
	
	public Subject(int subjNo) {
		this.subjNo = subjNo;
	}
	
	public Subject(String subjName) {
		this.subjName = subjName;
	}
	
	public Subject(int subjNo, String subjName) {
		this.subjNo = subjNo;
		this.subjName = subjName;
	}

	public int getSubjNo() {
		return subjNo;
	}

	public void setSubjNo(int subjNo) {
		this.subjNo = subjNo;
	}

	public String getSubjName() {
		return subjName;
	}

	public void setSubjName(String subjName) {
		this.subjName = subjName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + subjNo;
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
		Subject other = (Subject) obj;
		if (subjNo != other.subjNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", subjName, subjNo);
	}
	
	
}
