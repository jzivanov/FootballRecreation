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
	
	public InMemoryPlayerService()
	{
		User user = new User();
		user.setEmail("zjovan.ost@gmail.com");
		user.setFirstName("Jovan");
		user.setLastName("Zivanov");
		user.setPassword("123");
		user.setUsername("zjovan.ost@gmail.com");
		user.setId(1L);
		
		User user2 = new User();
		user2.setEmail("pera@kojot");
		user.setFirstName("Pera kojot");
		user.setLastName("Super genije");
		user2.setPassword("123");
		user2.setUsername("pera@kojot");
		user.setId(2L);
		
		User user3 = new User();
		user3.setEmail("bip@bip");
		user.setFirstName("Bip");
		user.setLastName("Bip");
		user3.setPassword("123");
		user3.setUsername("bip@bip");
		user.setId(3L);
		
		Exhibition exhibition = new Exhibition();
		exhibition.setExhibitionStart(new Date(2014, 8, 29, 20, 0));
		exhibition.setEnded(true);
		exhibition.setId(1L);
		
		Exhibition exhibition2 = new Exhibition();
		exhibition2.setExhibitionStart(new Date(2014, 8, 29, 21, 30));
		exhibition2.setEnded(false);
		exhibition.setId(2L);
		
		Exhibition exhibition3 = new Exhibition();
		exhibition3.setExhibitionStart(new Date(2014, 8, 30, 20, 0));
		exhibition3.setEnded(false);
		exhibition.setId(3L);
		
		Player player1 = new Player();
		player1.setExhibition(exhibition);
		player1.setTeam(Player.Team.Away);
		player1.setUser(user);
		
		Player player2 = new Player();
		player2.setExhibition(exhibition);
		player2.setTeam(Player.Team.Away);
		player2.setUser(user2);
		
		Player player3 = new Player();
		player3.setExhibition(exhibition);
		player3.setTeam(Player.Team.Home);
		player3.setUser(user3);
		
		Player player4 = new Player();
		player4.setExhibition(exhibition2);
		player4.setTeam(Player.Team.Away);
		player4.setUser(user);
		
		Player player5 = new Player();
		player5.setExhibition(exhibition2);
		player5.setTeam(Player.Team.Away);
		player5.setUser(user2);
		
		Player player6 = new Player();
		player6.setExhibition(exhibition2);
		player6.setTeam(Player.Team.Home);
		player6.setUser(user3);
		
		Player player7 = new Player();
		player7.setExhibition(exhibition3);
		player7.setTeam(Player.Team.Away);
		player7.setUser(user);
		
		save(player1);
		save(player2);
		save(player3);
		save(player4);
		save(player5);
		save(player6);
		save(player7);
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
