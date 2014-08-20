/**
 * 
 */
package rs.tfzr.FudbalT2.service.memory;

import org.springframework.stereotype.Service;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.ExhibitionService;
import rs.tfzr.FudbalT2.service.PlayerService;
import rs.tfzr.FudbalT2.service.UserService;

/**
 * @author Miroslav
 *
 */

@Service
public class InMemoryExhibitionService extends
		AbstractInMemoryService<Exhibition> implements ExhibitionService {

	private PlayerService playerService;

	private UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rs.tfzr.FudbalT2.service.ExhibitionService#addPlayer(rs.tfzr.FudbalT2
	 * .model.Exhibition)
	 */
	@Override
	public void addPlayer(Exhibition exhibition, Long userId) {
		User user = userService.findOne(userId);
		Player player = new Player(user, exhibition);
		player.setId(new Long(playerService.findAll().size()));
		playerService.save(player);
		exhibition.addPlayer(player);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rs.tfzr.FudbalT2.service.ExhibitionService#removePlayer(java.lang.Long,
	 * rs.tfzr.FudbalT2.model.Exhibition)
	 */
	@Override
	public void removePlayer(Long playerId, Exhibition exhibition) {
		if (playerId == null || playerId > exhibition.getPlayers().size()) {
			throw new IllegalArgumentException(String.format(
					"Error: Tried to delete non-existing entity with id=%d.",
					playerId));
		}
		Player player = playerService.findOne(playerId);
		playerService.remove(playerId);
		exhibition.removePlayer(player);

	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;

	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rs.tfzr.FudbalT2.service.ExhibitionService#addToTeam(java.lang.Long,
	 * rs.tfzr.FudbalT2.model.Player.Team)
	 */
	@Override
	public void addToTeam(Long playerId, Player.Team team) {
		Player player = playerService.findOne(playerId);
		player.setTeam(team);

	}

}
