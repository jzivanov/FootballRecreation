/**
 * 
 */
package rs.tfzr.FudbalT2.service.memory;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.User;

/**
 * @author Miroslav
 *
 */
public class InMemoryExhibitionServiceTest {

	private InMemoryExhibitionService inMemoryExhibitionService;
	private InMemoryUserService inMemoryUserService;
	private InMemoryPlayerService inMemoryPlayerService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		inMemoryExhibitionService = new InMemoryExhibitionService();
		inMemoryUserService = new InMemoryUserService();
		inMemoryPlayerService = new InMemoryPlayerService();
		inMemoryExhibitionService.setUserService(inMemoryUserService);
		inMemoryExhibitionService.setPlayerService(inMemoryPlayerService);
		Exhibition exhibition = new Exhibition();
		User user = new User();
		Player player = new Player();

		exhibition.setId(1L);

		exhibition.setExhibitionStart(new Date());

		user.setId(1L);
		user.setEmail("zjovan.ost@gmail.com");
		user.setFirstName("jovan");
		user.setLastName("zivanov");

		player.setId(1L);
		player.setUser(user);
		player.setExhibition(exhibition);

		inMemoryExhibitionService.save(exhibition);
		inMemoryUserService.save(user);
		inMemoryPlayerService.save(player);
	}

	/**
	 * Test method for
	 * {@link rs.tfzr.FudbalT2.service.memory.InMemoryExhibitionService#addPlayer(rs.tfzr.FudbalT2.model.Exhibition, java.lang.Long)}
	 * .
	 */
	@Test
	public void testAddPlayer() {
		Exhibition exhibition = inMemoryExhibitionService.findOne(1L);

		inMemoryExhibitionService.addPlayer(exhibition, 1L);

		Assert.assertNotNull(exhibition.getPlayers());
		Assert.assertTrue(exhibition.getPlayers().size() == 1);
		Assert.assertNotNull(exhibition.getPlayers().get(0));
	}

	/**
	 * Test method for
	 * {@link rs.tfzr.FudbalT2.service.memory.InMemoryExhibitionService#removePlayer(java.lang.Long, rs.tfzr.FudbalT2.model.Exhibition)}
	 * .
	 */
	@Test
	public void testRemovePlayer() {
		Exhibition exhibition = inMemoryExhibitionService.findOne(1L);

		inMemoryExhibitionService.addPlayer(exhibition, 1L);

		Assert.assertNotNull(exhibition.getPlayers());
		Assert.assertTrue(exhibition.getPlayers().size() == 1);
		Assert.assertNotNull(exhibition.getPlayers().get(0));

		inMemoryExhibitionService.removePlayer(1L, exhibition);

		Assert.assertNotNull(exhibition.getPlayers());
		Assert.assertTrue(exhibition.getPlayers().size() == 0);
	}

	/**
	 * Test method for
	 * {@link rs.tfzr.FudbalT2.service.memory.InMemoryExhibitionService#addToTeam(java.lang.Long, rs.tfzr.FudbalT2.model.Player.Team)}
	 * .
	 */
	@Test
	public void testAddToTeam() {
		Exhibition exhibition = inMemoryExhibitionService.findOne(1L);
		inMemoryExhibitionService.addPlayer(exhibition, 1L);
		inMemoryExhibitionService.addToTeam(inMemoryPlayerService.findOne(1L)
				.getId(), Player.Team.Home);
		Assert.assertNotNull(inMemoryPlayerService.findOne(1L).getTeam());
		Assert.assertTrue(inMemoryPlayerService.findOne(1L).getTeam() == Player.Team.Home);

	}

}
