package rs.tfzr.FudbalT2.service.memory;

import java.util.List;

import org.junit.Assert;

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
public class InMemoryScorerServiceTest {
	InMemoryScorerService service;

	@Before
	public void setUp() {
		service = new InMemoryScorerService();

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
		
		Scorers scorer1 = new Scorers();
		scorer1.setExhibition(exhibition);
		scorer1.setPlayer(player1);
		
		Scorers scorer2 = new Scorers();
		scorer2.setExhibition(exhibition);
		scorer2.setPlayer(player1);
		
		Scorers scorer3 = new Scorers();
		scorer3.setExhibition(exhibition2);
		scorer3.setPlayer(player3);
		
		
		service.save(scorer1);
		service.save(scorer2);
		service.save(scorer3);
	}

	@Test
	public void testFindByScorerId() {
		Scorers scorer = service.findOne(1L);

		Assert.assertNotNull(scorer);
		Assert.assertTrue(scorer.getPlayer().getId() == 1L);

		scorer = service.findOne(33L);

		Assert.assertNull(scorer);
	}

	@Test
	public void testFindAllScorers() {
		List<Scorers> list = service.listAllScorers(1L);
		Assert.assertTrue(list.size() == 2);
		list = service.listAllScorers(2L);
		Assert.assertTrue(list.size() == 1);
	}

	@Test
	public void testRankList() {
		List<Scorers> list = service.getRankList();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
		Scorers scorer = list.get(0);
		Assert.assertTrue(scorer.getId() == 1L);
	}
}
