package game.risk.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import game.risk.model.entities.Player;

public class GameReaderTest {

	@Test
	public void testLoad() {
		GameWriter gameWriter = new GameWriter("World_testingReinforcement");
		Player[] playerLoaded = gameWriter.readGame();
		assertNotNull(playerLoaded);
		assertEquals("Human", playerLoaded[0].getName());
		assertEquals(false, playerLoaded[0].isComputer());
		assertEquals("Aggressive", playerLoaded[1].getName());
		assertEquals(true, playerLoaded[1].isComputer());
	}
}
