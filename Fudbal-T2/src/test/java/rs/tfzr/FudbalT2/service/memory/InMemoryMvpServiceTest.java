package rs.tfzr.FudbalT2.service.memory;

import java.util.List;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.MVP;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.User;

/**
 * 
 * @author jovan
 *
 */
public class InMemoryMvpServiceTest {
	InMemoryMvpService service;

	@Before
	public void setUp() {
		service = new InMemoryMvpService();

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
		
		MVP mvp = new MVP(player1, exhibition, player2);
		MVP mvp2 = new MVP(player1, exhibition, player3);
		
		service.save(mvp);
		service.save(mvp2);
	}

	@Test
	public void testFindAllMVPs() {
		List<MVP> list = service.findAll();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void testMvpForExhibition() {
		Player player = service.getMvpForExhibition(1L);
		Assert.assertNotNull(player);
		Assert.assertTrue(player.getId() == 1L);
		Assert.assertNull(service.getMvpForExhibition(33L));
	}
}
