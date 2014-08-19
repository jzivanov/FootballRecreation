package rs.tfzr.FudbalT2.model;

public class Player extends AbstractBaseEntity {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public enum Team {
		None, Home, Away
	}

	public Player() {
		this.team = Team.None;
	}

	public Player(User user) {
		this();
		this.user = user;
	}

	private Team team;
	private User user;
	private Exhibition exhibition;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exhibition getExhibition() {
		return exhibition;
	}

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
