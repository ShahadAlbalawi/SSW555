import java.util.Date;

public class NuptialInfo {
	private String spouseOfFamilyId;
	private Date marriageDate;
	private Date divorceDate;
	
	public String getSpouseOfFamilyId() {
		return spouseOfFamilyId;
	}
	public void setSpouseOfFamilyId(String spouseOfFamilyId) {
		this.spouseOfFamilyId = spouseOfFamilyId;
	}
	public Date getMarriageDate() {
		return marriageDate;
	}
	public void setMarriageDate(Date marriageDate) {
		this.marriageDate = marriageDate;
	}
	public Date getDivorceDate() {
		return divorceDate;
	}
	public void setDivorceDate(Date divorceDate) {
		this.divorceDate = divorceDate;
	}
	@Override
	public String toString() {
		return "NuptialInfo [spouseOfFamilyId=" + spouseOfFamilyId + ", marriageDate=" + marriageDate + ", divorceDate="
				+ divorceDate + "]";
	}
}