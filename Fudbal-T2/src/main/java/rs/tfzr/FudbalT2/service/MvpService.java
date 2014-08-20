package rs.tfzr.FudbalT2.service;

import rs.tfzr.FudbalT2.model.MVP;

/**
 * 
 * @author jovan
 *
 */
public interface MvpService extends CrudService<MVP> {
	/**
	 * 
	 * @param ExhibitionId
	 * @return MVP for passed Exhibition or null if there is no mvp
	 */
	MVP getMvpForExhibition(Long ExhibitionId);
}
