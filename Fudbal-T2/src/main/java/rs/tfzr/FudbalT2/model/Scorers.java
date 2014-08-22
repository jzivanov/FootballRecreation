package rs.tfzr.FudbalT2.model;

/**
 * 
 * @author jovan
 *
 */
public class Scorers extends AbstractBaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7618031410436919734L;

	/**
	 * Player who scored
	 */
	private Player player;
	
	/**
	 * Scorer at exhibition
	 */
	private Exhibition exhibition;

	/**
	 * 
	 * @return {@link #player}
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * 
	 * @param {@link #player}
	 */
	public void setPlayer(Player player) {
		this.player = player;
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

	public Scorers() {
		this.player = new Player();
		this.exhibition = new Exhibition();
	}

	/**
	 * @param {@link #player}
	 */
	public Scorers(Player player) {
		this.player = player;
	}
	
	/**
	 * @param {@link #player}
	 * @param {@link #exhibition}
	 */
	public Scorers(Player player, Exhibition exhibition)
	{
		this.player = player;
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
		result = 31 * result + player.hashCode();
		result = 31 * result + exhibition.hashCode();
		return result;
	}
}
