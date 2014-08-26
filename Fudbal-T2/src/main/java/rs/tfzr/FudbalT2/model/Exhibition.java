package rs.tfzr.FudbalT2.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
	
	//sets the start date and time
	//calculates end time by adding 90minutes to the start time
	//formats both times
	public void setExhibitionStart(Date exhibitionStart) {
		String date = exhibitionStart.toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		date = dateFormat.format(exhibitionStart);
		try {
			this.exhibitionStart = dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(exhibitionStart);
		calendar.add(Calendar.HOUR, 1);
		calendar.add(Calendar.MINUTE, 30);
		String dateEnd = dateFormat.format(calendar.getTime());
		try {
			this.exhibitionEnd = dateFormat.parse(dateEnd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Date getExhibitionStart() {
		return exhibitionStart;
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
