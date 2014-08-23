package rs.tfzr.FudbalT2.service.memory;

import java.util.ArrayList;
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
	
	public InMemoryPlayerService()
	{
/*
		User user = new User();
		user.setId(1L);
		user.setEmail("zjovan.ost@gmail.com");
		user.setFirstName("jovan");
		user.setLastName("zivanov");
		
		User user2 = new User();
		user2.setId(2L);
		user2.setEmail("pera@kojot.supergenije");
		user2.setFirstName("pera kojot");
		user2.setLastName("super genije");
		
		User user3 = new User();
		user3.setId(3L);
		user3.setEmail("bip@bip.bip");
		user3.setFirstName("bip");
		user3.setLastName("bip");
		
		Exhibition exhibition = new Exhibition();
		exhibition.setId(1L);
		
		Exhibition exhibition2 = new Exhibition();
		exhibition2.setId(2L);
		
		Player player1 = new Player();
		player1.setId(1L);
		player1.setUser(user);
		player1.setExhibition(exhibition);

		Player player2 = new Player();
		player2.setId(2L);
		player2.setUser(user2);
		player2.setExhibition(exhibition);
		
		Player player3 = new Player();
		player3.setId(3L);
		player3.setUser(user3);
		player3.setExhibition(exhibition);

		Player player4 = new Player();
		player4.setId(4L);
		player4.setUser(user2);
		player4.setExhibition(exhibition2);
		
		Player player5 = new Player();
		player5.setId(5L);
		player5.setUser(user3);
		player5.setExhibition(exhibition2);
		
		this.save(player1);
		this.save(player2);
		this.save(player3);
		this.save(player4);
		this.save(player5);*/
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
