package rs.tfzr.FudbalT2.service;

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
	 * Check if {@link rs.tfzr.FudbalT2.model.Player Player} was already voted for mvp on {@link rs.tfzr.FudbalT2.model.Exhibition Exhibition}
	 * @param {@link rs.tfzr.FudbalT2.model.Player PlayerID}
	 * @param {@link rs.tfzr.FudbalT2.model.Exhibition ExhibitionID}
	 * @return {@code true} if player already voted, otherwise {@code false}
	 */
	boolean playerVoted(Long playerId, Long exhibitionId);
}
