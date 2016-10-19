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
		/*return "NuptialInfo [spouseOfFamilyId=" + spouseOfFamilyId + ", marriageDate=" + marriageDate + ", divorceDate="
				+ divorceDate + "]"; */
//User Story US01++
		 Date dateobj = new Date();
		 System.out.println(dateobj);
		if(this.marriageDate.after(dateobj) || this.divorceDate.after(dateobj))
		{
			return "Dates (marriage, divorce) should not be after the current date";
		}
		//User Story US01--
		
		//User Story US04++
		if(this.marriageDate.after(this.divorceDate))
		{
			return "Marriage should occur before divorce of spouses, and divorce can only occur after marriage";
		}
		return "NuptialInfo [spouseOfFamilyId=" + spouseOfFamilyId + ", marriageDate=" + marriageDate + ", divorceDate=" + divorceDate + "]";
	}
	//User Story Us04--

	}
}
