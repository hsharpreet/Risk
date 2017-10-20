package game.risk.test.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import game.risk.model.MapReader;
import game.risk.model.RiskMap;

/**
 * Class to test MapReader
 * 
 * @author Team
 *
 */

public class MapReaderTest {

	/**
	 * Method which will run before every test method
	 * 
	 * @throws java.lang.Exception
	 */

	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Method to test ContinentInCountries method for invalid continents in the
	 * countries
	 * 
	 * @throws Exception
	 */
	@Test
	public void testReadMapWithInvalidContinentInCountries() throws Exception {
		MapReader mapReader = new MapReader();
		String path = "World_WithinvalidContinentinCountry.map";
		RiskMap riskMap = mapReader.readMap(path);
		System.out.println("testReadMapWithInvalidContinentInCountries" + riskMap);
		assertNull(riskMap);

	}

	/**
	 * A method to test the functionality of the method if countries have no
	 * neighbors
	 * 
	 * @throws Exception
	 */
	@Test
	public void testReadMapCountrieswithNoNeighbours() throws Exception {
		MapReader mapReader = new MapReader();
		String path = "World_CountryWithNoNeighbours.map";
		RiskMap riskMap = mapReader.readMap(path);
		System.out.println("testReadMapCountrieswithNoNeighbours" + riskMap);
		assertNull(riskMap);

	}

	/**
	 * Method to test Read map countries if the country has asymmetric neighbors
	 * 
	 * @throws Exception
	 */
	@Test
	public void testReadMapCountrieswithAsymmetricNeighbours() throws Exception {
		MapReader mapReader = new MapReader();
		String path = "World_AsymmetricNeighbours.map";
		RiskMap riskMap = mapReader.readMap(path);
		System.out.println("testReadMapCountrieswithAsymmetricNeighbours" + riskMap);
		assertNull(riskMap);

	}

}