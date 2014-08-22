package rs.tfzr.FudbalT2.service;

import java.util.List;

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
	List<Scorers> listAllScorers(Long exhibitionId);

	/**
	 * Creates and returns the scorer rank list in the previous exhibitions
	 * @return {@link java.util.List List} of {@link rs.tfzr.FudbalT2.model.Scorers Scorers} sorted in descending order
	 */
	List<Scorers> getRankList();
}
