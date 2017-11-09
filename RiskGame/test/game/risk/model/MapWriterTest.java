package game.risk.model;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import game.risk.model.MapWriter;
import game.risk.util.Territory;

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

	/**
	 * Method to setup variables for each test
	 * @throws Exception unchecked
	 */
	@Before
	public void setUp() throws Exception {
		name = "new continet";
		path = "World.map";
		value = "5";
	}

	/**
	 * Method to test whether the continent is added in the file
	 * 
	 * @throws Exception unchecked
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
	 * @throws Exception unchecked
	 */
	@Test
	public void testCheckContinentIsDeleted() throws Exception {
		MapWriter writer = new MapWriter(path);
		MapReader reader = new MapReader();
		Map<String,Territory> territoriesOfContinent = reader.getTerritoriesOfContinent(name, path);
		writer.deleteContinent(name,territoriesOfContinent);

		assertFalse(name, new MapReader().readMap(path).getContinents().containsKey(name));

	}


}
