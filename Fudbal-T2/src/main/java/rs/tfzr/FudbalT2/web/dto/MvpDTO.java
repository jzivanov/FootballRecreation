package rs.tfzr.FudbalT2.web.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.User;

public class MvpDTO 
{
	private Date exhibitionStart;
	private Long playerId;
	private String playerFirstName;
	private String playerLastName;
	public Date getExhibitionStart() {
		return exhibitionStart;
	}
	public void setExhibitionStart(Date exhibitionStart) {
		this.exhibitionStart = exhibitionStart;
	}
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public String getPlayerFirstName() {
		return playerFirstName;
	}
	public void setPlayerFirstName(String playerFirstName) {
		this.playerFirstName = playerFirstName;
	}
	public String getPlayerLastName() {
		return playerLastName;
	}
	public void setPlayerLastName(String playerLastName) {
		this.playerLastName = playerLastName;
	}
}
