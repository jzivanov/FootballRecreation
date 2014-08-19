package rs.tfzr.FudbalT2.service;

import rs.tfzr.FudbalT2.model.MVP;

/**
 * 
 * @author jovan
 *
 */
public interface MvpService extends CrudService<MVP>
{
	/**
	 * 
	 * @param ExhibitionId
	 * @return MVP for passed Exhibition
	 */
	MVP getMvpForExhibition(Long ExhibitionId);
}