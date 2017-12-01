package game.risk.model;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import game.risk.model.entities.Player;

/**
 * Class to create Test cases for Game Reader
 * 
 * @author Team
 *
 */
public class GameReaderTest {
	/**
	 * Method to test the load functionality
	 */
	@Test
	public void testLoad() {
		GameReader gameReader = new GameReader("World_testingReinforcement_Game_Status.ser");
		Player[] playerLoaded = gameReader.readGame();
		assertNotNull(playerLoaded);
		assertEquals("Human", playerLoaded[0].getName());
		assertEquals(false, playerLoaded[0].isComputer());
		assertEquals("Aggressive", playerLoaded[1].getName());
		assertEquals(true, playerLoaded[1].isComputer());
	}

	/**
	 * Method to test whether game loads with empty player list
	 */
	@Test
	public void testLoadEmptyPlayerList() {
		GameReader gameReader = new GameReader("WorldFail_Game_Status.ser");
		Player[] playerLoaded = gameReader.readGame();
		assertNull(playerLoaded);
	}

	/**
	 * Method to test whether gane loads with invalid players
	 */
	@Test
	public void testLoadInvalidPlayers() {
		GameReader gameReader = new GameReader("World_testingReinforcement_Game_Status.ser");
		Player[] playerLoaded = gameReader.readGame();
		assertNotNull(playerLoaded);
		assertNotEquals("Aggressive", playerLoaded[0].getName());
		assertNotEquals(true, playerLoaded[0].isComputer());
	}
}
