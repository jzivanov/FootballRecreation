package rs.tfzr.FudbalT2.service.memory;

import java.util.List;

import junit.framework.Assert;

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

		MVP mvp = new MVP(player, exhibition);
		MVP mvp2 = new MVP(player2, exhibition2);

		service.save(mvp2);
		service.save(mvp);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testFindAllMVPs() {
		List<MVP> list = service.findAll();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testMvpForExhibition() {
		MVP mvp = service.getMvpForExhibition(2L);
		Assert.assertNotNull(mvp);
		Assert.assertNull(service.getMvpForExhibition(33L));
	}
}
