package rs.tfzr.FudbalT2.web.dto;

import java.util.Date;

import rs.tfzr.FudbalT2.model.Player.Team;

public class PlayerDTO {
	private Long id;
	private Team team;
	private Long userId;
	private String firstName;
	private String lastName;
	private Long exhibitionId;
	private Date exhibitionStart;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getExhibitionId() {
		return exhibitionId;
	}
	public void setExhibitionId(Long exhibitionId) {
		this.exhibitionId = exhibitionId;
	}
	public Date getExhibitionStart() {
		return exhibitionStart;
	}
	public void setExhibitionStart(Date exhibitionStart) {
		this.exhibitionStart = exhibitionStart;
	}
}
