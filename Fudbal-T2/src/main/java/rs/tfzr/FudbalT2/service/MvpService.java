package rs.tfzr.FudbalT2.service;

import java.util.List;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.MVP;
import rs.tfzr.FudbalT2.model.Player;

/**
 * 
 * @author jovan
 *
 */
public interface MvpService extends CrudService<MVP> {
	/**
	 * Find and return {@link rs.tfzr.FudbalT2.model.Player Player} with passed {@link rs.tfzr.FudbalT2.model.Exhibition ExhibitionID}
	 * @param {@link rs.tfzr.FudbalT2.model.Exhibition ExhibitionID}
	 * @return {@link rs.tfzr.FudbalT2.model.Player Player}, {@code null} if there is no entity
	 */
	Player getMvpForExhibition(Long ExhibitionId);
	
	/**
	 * Check if {@link rs.tfzr.FudbalT2.model.User User} already voted for mvp on {@link rs.tfzr.FudbalT2.model.Exhibition Exhibition}
	 * @param {@link rs.tfzr.FudbalT2.model.User UserID}
	 * @param {@link rs.tfzr.FudbalT2.model.Exhibition ExhibitionID}
	 * @return {@link rs.tfzr.FudbalT2.model.Player Player} that was voted, otherwise {@code null}
	 */
	Player userVoted(Long userId, Long exhibitionId);
	
	/**
	 * Find and return last available {@link rs.tfzr.FudbalT2.model.Exhibition Exhibition}
	 * @return {@link rs.tfzr.FudbalT2.model.Exhibition Exhibition} or {@code null}
	 */
	Exhibition getLastAvailable();
	
	/**
	 * Find and return all previous {@link rs.tfzr.FudbalT2.model.Player Players} that are voted for MVP
	 * @return {@code List} of players or empty {@code List}
	 */
	List<Player> getMvpHistory();
}
