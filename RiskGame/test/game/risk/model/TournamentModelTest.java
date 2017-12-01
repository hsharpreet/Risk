package game.risk.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import game.risk.model.TournamentModel;
import game.risk.model.entities.TournamentResult;;

/**
 * Class to create test cases for tournament model
 * 
 * @author Team
 *
 */
public class TournamentModelTest {
	/**
	 * Method to run before all test cases
	 * 
	 * @throws Exception run-time
	 *             
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Method to test the result count
	 */
	@Test
	public void testValidResultCount() {
		String[] player = { "Aggressive", "Benevolent", "Select", "Select" };
		String[] maps = { "World.map", "World.map" };
		int count = 3;
		int turn = 10;

		TournamentModel t = new TournamentModel(player, maps, count, turn);
		t.startTournament();

		List<TournamentResult> bb = t.getTournamentResult();

		assertEquals(6, bb.size());
	}

	/**
	 * Method to test the result count
	 */
	@Test
	public void testFalseValidResultCount() {
		String[] player = { "Aggressive", "Benevolent", "Select", "Select" };
		String[] maps = { "World.map", "World.map" };
		int count = 3;
		int turn = 10;

		TournamentModel t = new TournamentModel(player, maps, count, turn);
		t.startTournament();

		List<TournamentResult> bb = t.getTournamentResult();

		assertNotEquals(2, bb.size());
	}

}
