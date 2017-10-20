package game.risk.test.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.risk.util.MapReader;
import game.risk.util.MapWriter;
import game.risk.util.RiskMap;

/**
 * Class to test all the methods of MapWriter
 * 
 * @author Team
 *
 */
public class MapWriterTest {

	private String name = null;
	private String path = null;
	private String value = null;

	@Before
	public void setUp() throws Exception {
		name = "new continet";
		path = "World.map";
		value = "5";
	}

	/**
	 * Method to test whether the continent is added in the file
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCheckContinentIsAdded() throws Exception {
		MapWriter writer = new MapWriter(path);
		writer.addContinent(name, value);

		assertTrue(name, new MapReader().readMap(path).getContinents().containsKey(name));

	}

	/**
	 * Method to test whether continent is added in the file.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCheckContinentIsDeleted() throws Exception {
		MapWriter writer = new MapWriter(path);
		writer.deleteContinent(name);

		assertFalse(name, new MapReader().readMap(path).getContinents().containsKey(name));

	}

}
