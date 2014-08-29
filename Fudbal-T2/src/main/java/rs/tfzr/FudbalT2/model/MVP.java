package rs.tfzr.FudbalT2.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MVP model class
 * 
 * @author jovan
 */
@Entity
@Table(name = "mvp")
public class MVP extends AbstractBaseEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9116603249119354912L;

	/**
	 * Voted player
	 */
	@ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
	private Player player;
	
	/**
	 * Exhibition of voted player
	 */
	@ManyToOne
    @JoinColumn(name = "exhibition_id", nullable = false)
	private Exhibition exhibition;
	
	/**
	 * Player, who votes
	 */
	private Player playerVote;

	/**
	 * @return {@link #player}
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param {@link #player}
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return {@link #exhibition}
	 */
	public Exhibition getExhibition() {
		return exhibition;
	}

	/**
	 * @param {@link #exhibition}
	 */
	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}

	/**
	 * @return {@link #playerVote}
	 */
	public Player getPlayerVote() {
		return playerVote;
	}

	/**
	 * @param {@link #playerVote}
	 */
	public void setPlayerVote(Player playerVote) {
		this.playerVote = playerVote;
	}

	public MVP() {
		player = new Player();
		exhibition = new Exhibition();
		playerVote = new Player();
	}

	/**
	 * 
	 * @param {@link #player}
	 * @param {@link #exhibition}
	 * @param {@link #playerVote}
	 */
	public MVP(Player player, Exhibition exhibition, Player playerVote) {
		this.player = player;
		this.exhibition = exhibition;
		this.playerVote = playerVote;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + player.hashCode();
		result = 31 * result + exhibition.hashCode();
		return result;
	}
}
