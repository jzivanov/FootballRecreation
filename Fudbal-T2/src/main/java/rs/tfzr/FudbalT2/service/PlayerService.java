package rs.tfzr.FudbalT2.service;

import java.util.List;

import rs.tfzr.FudbalT2.model.Player;

/**
 * 
 * @author jovan
 *
 */
public interface PlayerService extends CrudService<Player> {
	/**
	 * Return back all {@link rs.tfzr.FudbalT2.model.Player Players} at {@link rs.tfzr.FudbalT2.model.Exhibition Exhibition}
	 * @param {@link rs.tfzr.FudbalT2.model.Exhibition ExhibitionID}
	 * @return {@link java.util.List List} of existing entities, empty {@code List} if there are no entities
	 */
	List<Player> findAll(Long exhibitionId);
	/**
	 * Register player to play in {@link rs.tfzr.FudbalT2.model.Player.Team Team}
	 * @param {@link rs.tfzr.FudbalT2.model.Player PlayerID}
	 * @param {@link rs.tfzr.FudbalT2.model.Player.Team Team}
	 */
	void addToTeam(Long playerId, Player.Team team);
	
	/**
	 * Find and return {@link rs.tfzr.FudbalT2.model.Player Player} with passed {@link rs.tfzr.FudbalT2.model.User UserID}.
	 * @param {@link rs.tfzr.FudbalT2.model.User UserID}
	 * @param {@link rs.tfzr.FudbalT2.model.Exhibition Exhibition}
	 * @return {@link rs.tfzr.FudbalT2.model.Player Player}, {@code null} if there is no entity
	 */
	Player findOne(Long userId, Long exhibitionId);
}
