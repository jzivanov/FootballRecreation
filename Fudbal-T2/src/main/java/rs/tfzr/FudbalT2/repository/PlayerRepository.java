package rs.tfzr.FudbalT2.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.tfzr.FudbalT2.model.Player;

/**
 * Player jpa access layer interface
 * 
 * @author jovan
 *
 */
public interface PlayerRepository extends Repository<Player, Long> {
	/**
	 * Find and return Player with passed id
	 * 
	 * @param id
	 *            of the entity to return
	 * @return entity with passed id or null if not found
	 */
	Player findOne(Long id);

	/**
	 * Return back all existing entities.
	 *
	 * @return list of existing entities, empty list if there are no entities
	 */
	List<Player> findAll();

	/**
	 * Save entity and return saved instance (with id set).
	 *
	 * @param Player
	 *            to be saved
	 * @return saved instance
	 */
	Player save(Player player);

	/**
	 * Remove entity with passed id.
	 *
	 * @param id
	 *            of the entity to be removed
	 * @throws IllegalArgumentException
	 *             if there is no entity with passed id
	 */
	void delete(Long id) throws IllegalArgumentException;

	/**
	 * List of all players at Exhibition
	 * 
	 * @param exhibitionId
	 * @return list of all players at Exhibition, IllegalArgumentException if
	 *         there is no entity with passed id
	 */
	List<Player> listAllPlayersOfExhibition(Long exhibitionId);
}
