package rs.tfzr.FudbalT2.service;

import java.util.List;
import java.util.Map;

import rs.tfzr.FudbalT2.model.Scorers;

/**
 * 
 * @author jovan
 *
 */
public interface ScorerService extends CrudService<Scorers> {
	/**
	 * Return back all {@link rs.tfzr.FudbalT2.model.Scorers Scorers} at  {@link rs.tfzr.FudbalT2.model.Exhibition Exhibition}
	 * @param {@link rs.tfzr.FudbalT2.model.Exhibition ExhibitionID}
	 * @return {@link java.util.List List} of existing entities, empty {@code list} if there are no entities
	 */
	Map<Scorers, Short> getRankList(Long exhibitionId);

	/**
	 * Creates and returns the scorer rank list in the previous exhibitions
	 * @return {@link java.util.List List} of {@link rs.tfzr.FudbalT2.model.Scorers Scorers} sorted in descending order
	 */
	Map<Scorers, Short> getRankList();
	
	/**
	 * Returns players score count at all exhibitions
	 * @param {@link rs.tfzr.FudbalT2.model.Player PlayerID}
	 * @return Player score count or {@code 0}
	 */
	short getUserScoreCount(Long playerId);
	
	/**
	 * Returns players score count at passed {@link rs.tfzr.FudbalT2.model.Exhibition Exhibition}
	 * @param {@link rs.tfzr.FudbalT2.model.Player PlayerID}
	 * @param {@link rs.tfzr.FudbalT2.model.Exhibition ExhibitionID}
	 * @return Player score count or {@code 0}
	 */
	byte getPlayerScoreCount(Long playerId, Long exhibitionId);
}
