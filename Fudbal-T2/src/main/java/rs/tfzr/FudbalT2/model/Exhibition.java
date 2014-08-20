package rs.tfzr.FudbalT2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Exhibition extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7168512930522429680L;

	private Date exhibitionStart;

	private Date exhibitionEnd;

	private boolean ended;

	private List<Scorers> scorers;

	private List<Player> players;

	public List<Player> getPlayers() {
		return players;
	}

	public Exhibition() {
		players = new ArrayList<Player>();
	}

	public void addPlayer(Player player) {
		this.players.add(player);
	}

	public void removePlayer(Player player) {
		players.remove(player);
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Scorers> getScorers() {
		return scorers;
	}

	public void setScorers(List<Scorers> scorers) {
		this.scorers = scorers;
	}

	public void setExhibitionStart(Date exhibitionStart) {
		this.exhibitionStart = exhibitionStart;
	}

	public Date getExhibitionStart() {
		return exhibitionStart;
	}

	public void setExhibitionEnd(Date exhibitionEnd) {
		this.exhibitionEnd = exhibitionEnd;
	}

	public Date getExhibitionEnd() {
		return exhibitionEnd;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public boolean getEnded() {
		return ended;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;

		Exhibition exhibition = (Exhibition) o;

		if (exhibitionStart != exhibition.exhibitionStart)
			return false;

		return true;
	}

}
