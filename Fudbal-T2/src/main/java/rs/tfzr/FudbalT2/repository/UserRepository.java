/**
 * 
 */
package rs.tfzr.FudbalT2.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.tfzr.FudbalT2.model.User;

/**
 * @author Miroslav
 *
 */
public interface UserRepository extends Repository<User, Long> {
	/**
	 * Find and return entity with passed id.
	 *
	 * @param id
	 *            of the entity to return
	 * @return entity with passed id or null if not found
	 */
	User findOne(Long id);

	/**
	 * Return back all existing entities.
	 *
	 * @return list of existing entities, empty list if there are no entities
	 */
	List<User> findAll();

	/**
	 * Save entity and return saved instance (with id set).
	 *
	 * @param category
	 *            to be saved
	 * @return saved instance
	 */
	User save(User category);

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
