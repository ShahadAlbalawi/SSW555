import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person  {
	private String id;
	private String fName;
	private String lName;
	private String sex;
	private Date birthDate;
	private Date deathDate;
	private String childOfFamilyId;
	private List<NuptialInfo> nuptials = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}
	public String getChildOfFamilyId() {
		return childOfFamilyId;
	}
	public void setChildOfFamilyId(String childOfFamilyId) {
		this.childOfFamilyId = childOfFamilyId;
	}
	public List<NuptialInfo> getNuptials() {
		return nuptials;
	}
	public void addNuptials(NuptialInfo nuptial) {
		this.nuptials.add(nuptial);
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", fName=" + fName + ", lName=" + lName + ", sex=" + sex + ", birthDate="
				+ birthDate + ", deathDate=" + deathDate;
				/*+ ", childOfFamilyId=" + childOfFamilyId + ", nuptials="+ nuptials + "]";*/
	}
	 

}
