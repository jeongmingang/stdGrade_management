package stdGrade_management.dto;

public class Ban {
	private String ban;

	public Ban() {
		
	}

	public Ban(String ban) {
		this.ban = ban;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ban == null) ? 0 : ban.hashCode());
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
		Ban other = (Ban) obj;
		if (ban == null) {
			if (other.ban != null)
				return false;
		} else if (!ban.equals(other.ban))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s", ban);
	}
}
