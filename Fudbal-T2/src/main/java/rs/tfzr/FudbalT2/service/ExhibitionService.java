/**
 * 
 */
package rs.tfzr.FudbalT2.service;

import java.util.List;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.Scorers;

/**
 * @author Miroslav
 *
 */
public interface ExhibitionService extends CrudService<Exhibition> {
	// Returns all players in one exhibition
	// @returns list of players
	List<Player> listAllPlayers();

	// Add a new player to the exhibition
	// @param exhibition to which the player is added
	void addPlayer(Exhibition exhibition);

	// removes a player from the exhibition
	// @param playerId - id of the player that is supposed to be removed
	// @param exhibition from which he is going to be removed
	void removePlayer(Long playerId, Exhibition exhibition);

	void addToTeam(Long playerId, int team);

}
