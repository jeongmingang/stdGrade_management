package stdGrade_management.dto;

import java.util.Arrays;
import java.util.Date;

public class StudentDetail {
	private int stdNo;
	private boolean gender;
	private Date birthday;
	private byte[] pic;
	
	public StudentDetail() {
		
	}

	public StudentDetail(int stdNo) {
		this.stdNo = stdNo;
	}

	public StudentDetail(int stdNo, boolean gender, Date birthday, byte[] pic) {
		this.stdNo = stdNo;
		this.gender = gender;
		this.birthday = birthday;
		this.pic = pic;
	}

	public int getStdNo() {
		return stdNo;
	}

	public void setStdNo(int stdNo) {
		this.stdNo = stdNo;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
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
		StudentDetail other = (StudentDetail) obj;
		if (stdNo != other.stdNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("stdNo=%s, gender=%s, birthday=%s, pic=%s", stdNo, gender, birthday,
				Arrays.toString(pic));
	}
	
	
}
