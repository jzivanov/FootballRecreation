package rs.tfzr.FudbalT2.service.memory;

import java.util.List;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.User;

public class InMemoryPlayerServiceTest 
{
	InMemoryPlayerService service;
	
	@Before
	public void setUp() {
		service = new InMemoryPlayerService();

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
		
		service.save(player1);
		service.save(player2);
		service.save(player3);
		service.save(player4);
		service.save(player5);
	}
	
	@Test
	public void findAllTest()
	{
		List<Player> list = service.findSignedPlayers(1L);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() == 3);
		list = service.findSignedPlayers(2L);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() == 2);
		list = service.findSignedPlayers(34L);
		Assert.assertTrue(list.isEmpty());
	}
	
	@Test
	public void addToTeamTest()
	{
		Player player = service.findOne(1L);
		Assert.assertTrue(player.getTeam() == Player.Team.None);
		service.addToTeam(player.getId(), Player.Team.Away);
		service.addToTeam(2L, Player.Team.Away);
		Player player2 = service.findOne(2L);
		Assert.assertTrue(player.getTeam() == player2.getTeam() && player.getTeam() == Player.Team.Away);
	}
	
	@Test
	public void findOneTest()
	{
		Player player = service.findOne(1L, 1L);
		Assert.assertTrue(player.getUser().getFirstName() == "jovan");
		player = service.findOne(2L, 1L);
		Assert.assertTrue(player.getUser().getFirstName() == "pera kojot");
		player = service.findOne(3L, 1L);
		Assert.assertTrue(player.getUser().getFirstName() == "bip");
		player = service.findOne(2L, 2L);
		Assert.assertTrue(player.getUser().getFirstName() == "pera kojot");
		player = service.findOne(3L, 2L);
		Assert.assertTrue(player.getUser().getFirstName() == "bip");
		
	}
}
