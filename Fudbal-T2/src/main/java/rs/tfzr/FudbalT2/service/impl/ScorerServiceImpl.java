/**
 * 
 */
package rs.tfzr.FudbalT2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import rs.tfzr.FudbalT2.model.Scorers;
import rs.tfzr.FudbalT2.repository.ScorerRepository;
import rs.tfzr.FudbalT2.service.ScorerService;

/**
 * @author Miroslav
 *
 */
public class ScorerServiceImpl implements ScorerService {

	@Autowired
	private ScorerRepository scorerRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see rs.tfzr.FudbalT2.service.CrudService#findOne(java.lang.Long)
	 */
	@Override
	public Scorers findOne(Long id) {
		return scorerRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rs.tfzr.FudbalT2.service.CrudService#findAll()
	 */
	@Override
	public List<Scorers> findAll() {
		return scorerRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rs.tfzr.FudbalT2.service.CrudService#save(rs.tfzr.FudbalT2.model.
	 * AbstractBaseEntity)
	 */
	@Override
	public Scorers save(Scorers scorers) {
		return scorerRepository.save(scorers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rs.tfzr.FudbalT2.service.CrudService#remove(java.lang.Long)
	 */
	@Override
	public void remove(Long id) throws IllegalArgumentException {
		scorerRepository.delete(id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rs.tfzr.FudbalT2.service.ScorerService#listAllScorers(java.lang.Long)
	 */
	@Override
	public List<Scorers> listAllScorers(Long exhibitionId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rs.tfzr.FudbalT2.service.ScorerService#getRankList()
	 */
	@Override
	public List<Scorers> getRankList() {
		// TODO Auto-generated method stub
		return null;
	}

}
