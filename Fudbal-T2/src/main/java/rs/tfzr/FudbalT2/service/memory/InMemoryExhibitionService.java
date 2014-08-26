/**
 * 
 */
package rs.tfzr.FudbalT2.service.memory;

import java.util.Date;

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
	
	public InMemoryExhibitionService() {
				
		Exhibition exhibition = new Exhibition();
		exhibition.setId(1L);
		
		Date start = new Date();
		Date end = new Date();
		end.setTime(start.getTime() + 60*60+1000);
		
		exhibition.setExhibitionStart(start);
		exhibition.setExhibitionEnd(end);
		exhibition.setEnded(false);
		
		Exhibition exhibition2 = new Exhibition();
		exhibition2.setId(2L);
		
		Date start2 = new Date();
		Date end2 = new Date();
		end2.setTime(start2.getTime() + 60*60+1000);
		
		exhibition2.setExhibitionStart(start2);
		exhibition2.setExhibitionEnd(end2);
		exhibition2.setEnded(false);
		
		Exhibition exhibition3 = new Exhibition();
		exhibition3.setId(3L);
		
		Date start3 = new Date();
		Date end3 = new Date();
		end3.setTime(start3.getTime() + 60*60+1000);
		
		exhibition3.setExhibitionStart(start3);
		exhibition3.setExhibitionEnd(end3);
		exhibition3.setEnded(false);
		
		this.save(exhibition);
		this.save(exhibition2);
		this.save(exhibition3);
	}

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
		playerService.save(player);
	}

}
