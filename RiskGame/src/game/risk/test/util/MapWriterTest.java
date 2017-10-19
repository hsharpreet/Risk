package game.risk.test.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.risk.util.MapReader;
import game.risk.util.MapWriter;
import game.risk.util.RiskMap;

public class MapWriterTest {
	
	private String name =null;
	private String path = null;
	private String value = null;
	@Before
	public void setUp() throws Exception {
		 name = "new continet";
		 path = "World.map";
		 value = "5";
	}

	@Test
	public void testCheckContinentIsAdded() throws Exception {
		MapWriter writer = new MapWriter(path);
		writer.addContinent(name, value);
		
		assertTrue(name, new MapReader().readMap(path).getContinents().containsKey(name));

	}
	
	
	@Test
	public void testCheckContinentIsDeleted() throws Exception {
		MapWriter writer = new MapWriter(path);
		writer.deleteContinent(name);

		assertFalse(name, new MapReader().readMap(path).getContinents().containsKey(name));

	}
	

}
