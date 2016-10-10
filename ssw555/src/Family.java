import java.util.ArrayList;
import java.util.List;

public class Family {
	private String id;
	private Person husband;
	private Person wife;
	private List<Person> children = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Person getHusband() {
		return husband;
	}
	public void setHusband(Person husband) {
		this.husband = husband;
	}
	public Person getWife() {
		return wife;
	}
	public void setWife(Person wife) {
		this.wife = wife;
	}
	public List<Person> getChildren() {
		return children;
	}
	public void addChildren(Person child) {
		this.children.add(child);
	}
	
	@Override
	public String toString() {
		return "ID=" + id + ", Husband=" + husband + ", Wife=" + wife + ", children=" + children + "]";
	}
}