package game.risk.model;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import game.risk.model.entities.Player;

public class GameReaderTest {

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
	
	@Test
	public void testLoadEmptyPlayerList() {
		GameReader gameReader = new GameReader("WorldFail_Game_Status.ser");
		Player[] playerLoaded = gameReader.readGame();
		assertNull(playerLoaded);
	}
	
	@Test
	public void testLoadInvalidPlayers() {
		GameReader gameReader = new GameReader("World_testingReinforcement_Game_Status.ser");
		Player[] playerLoaded = gameReader.readGame();
		assertNotNull(playerLoaded);
		assertNotEquals("Aggressive", playerLoaded[0].getName());
		assertNotEquals(true, playerLoaded[0].isComputer());
	}
}
