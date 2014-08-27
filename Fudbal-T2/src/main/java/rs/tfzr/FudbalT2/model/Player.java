package rs.tfzr.FudbalT2.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author jovan
 *
 */
@Entity
@Table(name = "player")
public class Player extends AbstractBaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 209528800293838347L;

	/**
	 * It is used to distinguish teams. Values can be: 
	 * None (Team not set); Home; Away 
	 */
	public enum Team {
		None, Home, Away
	}

	/**
	 * Player {@link Team}
	 */
	private Team team;
	
	/**
	 * Represents player
	 */
	private User user;
	
	/**
	 * Player's exhibition
	 */
	private Exhibition exhibition;

	public Player() {
		this.team = Team.None;
		this.user = new User();
		this.exhibition = new Exhibition();
	}

	/**
	 * @param {@link #user}
	 * @param {@link #exhibition}
	 */
	public Player(User user, Exhibition exhibition) {
		this.team = Team.None;
		this.user = user;
		this.exhibition = exhibition;
	}

	/**
	 * @param {@link #user}
	 * @param {@link #exhibition}
	 * @param {@link Team}
	 */
	public Player(User user, Exhibition exhibition, Team team) {
		this.user = user;
		this.exhibition = exhibition;
		this.team = team;
	}

	/**
	 * @return {@link #team}
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @param {@link #team}
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * @return {@link #user}
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 
	 * @param {@link #user}
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 
	 * @return {@link #exhibition}
	 */
	public Exhibition getExhibition() {
		return exhibition;
	}

	/**
	 * 
	 * @param {@link #exhibition}
	 */
	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
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
		result = 31 * result + user.hashCode();
		result = 31 * result + exhibition.hashCode();
		result = 31 * result + team.hashCode();
		return result;
	}
}
