package rs.tfzr.FudbalT2.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.tfzr.FudbalT2.model.MVP;

public interface MvpRepository extends Repository<MVP, Long>
{
	/**
	 * Find and return MVP with passed id
	 * 
	 * @param id of the entity to return
	 * @return entity with passed id or null if not found
	 */
	MVP findOne(Long id);

    /**
     * Return back all existing entities.
     *
     * @return list of existing entities, empty list if there are no entities
     */
    List<MVP> findAll();

    /**
     * Save entity and return saved instance (with id set).
     *
     * @param  MVP to be saved
     * @return saved instance
     */
    MVP save(MVP mvp);

    /**
     * Remove entity with passed id.
     *
     * @param id of the entity to be removed
     * @throws IllegalArgumentException if there is no entity with passed id
     */
    void delete(Long id) throws IllegalArgumentException;

	/**
	 * MVP of Exhibition
	 * 
	 * @param ExhibitionId
	 * @return MVP for passed Exhibition
	 */
	MVP getMvpForExhibition(Long ExhibitionId);
}
