package rs.tfzr.FudbalT2.web.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.UserDetail;

public class MvpDTO 
{
	@NotNull
	private Exhibition exhibition;
	@NotNull
	private UserDetail user;
	
	private List<Player> playerList;
	
	public Exhibition getExhibition() {
		return exhibition;
	}
	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}
	public UserDetail getUser() {
		return user;
	}
	public void setUser(UserDetail user) {
		this.user = user;
	}
	public List<Player> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}
}
