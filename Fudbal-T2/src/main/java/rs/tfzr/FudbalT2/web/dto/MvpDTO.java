package rs.tfzr.FudbalT2.web.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.User;

public class MvpDTO 
{
	@NotNull
	private Exhibition exhibition;
	@NotNull
	private User user;
	
	private List<Player> players;
	
	public Exhibition getExhibition() {
		return exhibition;
	}
	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Player> getPlayerList() {
		return players;
	}
	public void setPlayerList(List<Player> playerList) {
		this.players = playerList;
	}
}
