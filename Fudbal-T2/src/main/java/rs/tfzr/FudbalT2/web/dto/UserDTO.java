package rs.tfzr.FudbalT2.web.dto;

public class UserDTO 
{
	private Long id;
	private String first;
	private String last;
	private String username;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String firstName) {
		this.first = firstName;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String lastName) {
		this.last = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
