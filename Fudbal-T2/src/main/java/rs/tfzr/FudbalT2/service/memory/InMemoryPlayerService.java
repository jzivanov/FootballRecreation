package rs.tfzr.FudbalT2.service.memory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.Player.Team;
import rs.tfzr.FudbalT2.service.PlayerService;

@Service
public class InMemoryPlayerService extends AbstractInMemoryService<Player>
		implements PlayerService {
	
	public InMemoryPlayerService()
	{
		/*Exhibition exhibition = new Exhibition();
		exhibition.setId(1L);
		User user = new User();
		user.setId(1L);
		user.setEmail("zjovan.ost@gmail.com");
		user.setFirstName("jovan");
		user.setLastName("zivanov");
		Player player = new Player();
		player.setId(1L);
		player.setUser(user);
		player.setExhibition(exhibition);

		// 2
		Exhibition exhibition2 = new Exhibition();
		exhibition2.setId(2L);
		User user2 = new User();
		user2.setId(2L);
		user2.setEmail("zjovan2.ost@gmail.com");
		user2.setFirstName("jovan2");
		user2.setLastName("zivanov2");
		Player player2 = new Player();
		player2.setId(2L);
		player2.setUser(user2);
		player2.setExhibition(exhibition2);

		// 3
		Exhibition exhibition3 = new Exhibition();
		exhibition3.setId(3L);
		Player player3 = new Player();
		player3.setId(3L);
		player3.setUser(user2);
		player3.setExhibition(exhibition3);

		Player player4 = new Player();
		player4.setId(4L);
		player4.setUser(user2);
		player4.setExhibition(exhibition3);
		
		save(player);
		save(player2);
		save(player3);
		save(player4);*/
	}
	
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
