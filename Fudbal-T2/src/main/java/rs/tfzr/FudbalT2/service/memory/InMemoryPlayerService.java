package rs.tfzr.FudbalT2.service.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.model.Player.Team;
import rs.tfzr.FudbalT2.service.PlayerService;

@Service
public class InMemoryPlayerService extends AbstractInMemoryService<Player>
		implements PlayerService {
	
	@Override
	public List<Player> findAll(Long exhibitionId) {
		List<Player> list = new ArrayList<>();
		for (Player p : findAll()) {
			if (p.getExhibition().getId() == exhibitionId)
				list.add(p);
		}
		return list;
	}

	@Override
	public void addToTeam(Long playerId, Team team) 
	{
		Player player = findOne(playerId);
		player.setTeam(team);
		save(player);
	}

	@Override
	public Player findOne(Long userId, Long exhibitionId) {
		Player res = null;
		for(Player player: findAll())
		{
			if(player.getUser().getId() == userId && player.getExhibition().getId() == exhibitionId)
			{
				res = player;
				break;
			}
		}
		return res;
	}

}
