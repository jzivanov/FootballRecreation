package rs.tfzr.FudbalT2.service.memory;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.Scorers;
import rs.tfzr.FudbalT2.model.User;

/**
 * Test class for {@link InMemoryScorerService}
 * 
 * @author jovan
 */
public class InMemoryScorerServiceTest 
{
	InMemoryScorerService service;
	
	@Before
	public void setUp()
	{
		service = new InMemoryScorerService();
		
		Exhibition exhibition = new Exhibition();
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

		Scorers scorer = new Scorers();
		scorer.setId(1L);
		scorer.setExhibition(exhibition);
		scorer.setPlayer(player);
		
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

		Scorers scorer2 = new Scorers();
		scorer2.setId(2L);
		scorer2.setExhibition(exhibition2);
		scorer2.setPlayer(player2);
		
		// 3
		Exhibition exhibition3 = new Exhibition();
		exhibition3.setId(3L);
		Player player3 = new Player();
		player3.setId(3L);
		player3.setUser(user2);
		player3.setExhibition(exhibition3);

		Scorers scorer3 = new Scorers();
		scorer3.setId(3L);
		scorer3.setExhibition(exhibition3);
		scorer3.setPlayer(player3);
		

		Player player4 = new Player();
		player4.setId(4L);
		player4.setUser(user2);
		player4.setExhibition(exhibition3);
		Scorers scorer4 = new Scorers();
		scorer4.setId(4L);
		scorer4.setExhibition(exhibition3);
		scorer4.setPlayer(player4);
		
		service.save(scorer);
		service.save(scorer2);
		service.save(scorer3);
		service.save(scorer4);
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testFindByScorerId() 
	{
		Scorers scorer = service.findOne(1L);
		
		Assert.assertNotNull(scorer);
		Assert.assertTrue(scorer.getPlayer().getId() == 1L);
		
		scorer = service.findOne(33L);
		
		Assert.assertNull(scorer);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testFindAllScorers()
	{
		List<Scorers> list = service.listAllScorers(1L);
		Assert.assertTrue(list.size() == 1);
		list = service.listAllScorers(3L);
		Assert.assertTrue(list.size() == 2);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testRankList()
	{
		List<Scorers> list = service.getRankList();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
		Scorers scorer = list.get(0);
		Assert.assertTrue(scorer.getId() == 2L);
	}
}
