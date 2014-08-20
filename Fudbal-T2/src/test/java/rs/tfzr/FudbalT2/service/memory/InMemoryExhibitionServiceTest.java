/**
 * 
 */
package rs.tfzr.FudbalT2.service.memory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rs.tfzr.FudbalT2.model.Exhibition;

/**
 * @author Miroslav
 *
 */
public class InMemoryExhibitionServiceTest {

	private InMemoryExhibitionService inMemoryService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		inMemoryService = new InMemoryExhibitionService();
		Exhibition exhibition = new Exhibition();
	}

	/**
	 * Test method for
	 * {@link rs.tfzr.FudbalT2.service.memory.InMemoryExhibitionService#addPlayer(rs.tfzr.FudbalT2.model.Exhibition, java.lang.Long)}
	 * .
	 */
	@Test
	public void testAddPlayer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link rs.tfzr.FudbalT2.service.memory.InMemoryExhibitionService#removePlayer(java.lang.Long, rs.tfzr.FudbalT2.model.Exhibition)}
	 * .
	 */
	@Test
	public void testRemovePlayer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link rs.tfzr.FudbalT2.service.memory.InMemoryExhibitionService#addToTeam(java.lang.Long, rs.tfzr.FudbalT2.model.Player.Team)}
	 * .
	 */
	@Test
	public void testAddToTeam() {
		fail("Not yet implemented");
	}

}
