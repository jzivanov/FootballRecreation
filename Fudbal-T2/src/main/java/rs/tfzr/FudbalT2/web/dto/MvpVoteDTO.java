package rs.tfzr.FudbalT2.web.dto;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;

public class MvpVoteDTO {
	Exhibition exhibition;
	Player player;
	public Exhibition getExhibition() {
		return exhibition;
	}
	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
}
