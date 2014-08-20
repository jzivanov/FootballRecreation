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
	 * 
	 * @param exhibitionId
	 * @return List of all scorers in given Exhibition
	 */
	List<Scorers> listAllScorers(Long exhibitionId);

	/**
	 * 
	 * @return List of scorers sorted in descending order
	 */
	List<Scorers> getRankList();
}
