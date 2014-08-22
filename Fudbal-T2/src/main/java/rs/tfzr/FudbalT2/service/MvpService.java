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
}
