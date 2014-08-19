/**
 * 
 */
package rs.tfzr.FudbalT2.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.tfzr.FudbalT2.model.Scorers;

/**
 * @author Miroslav
 *
 */
public interface ScorerRepository extends Repository<Scorers, Long> {
	/**
	 * Find and return entity with passed id.
	 *
	 * @param id
	 *            of the entity to return
	 * @return entity with passed id or null if not found
	 */
	Scorers findOne(Long id);

	/**
	 * Return back all existing entities.
	 *
	 * @return list of existing entities, empty list if there are no entities
	 */
	List<Scorers> findAll();

	/**
	 * Save entity and return saved instance (with id set).
	 *
	 * @param exhibition
	 *            to be saved
	 * @return saved instance
	 */
	Scorers save(Scorers scorers);

	/**
	 * Remove entity with passed id.
	 *
	 * @param id
	 *            of the entity to be removed
	 * @throws IllegalArgumentException
	 *             if there is no entity with passed id
	 */
	void delete(Long id) throws IllegalArgumentException;
}
