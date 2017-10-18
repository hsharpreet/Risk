package game.risk.test.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import game.risk.util.MapReader;
import game.risk.util.RiskMap;

/**
 * Class to test MapReader
 * @author Team
 *
 */

public class MapReaderTest {

	/**
	 * Method which will run before every test method
	 * @throws java.lang.Exception
	 */
	
	@Before
	public void setUp() throws Exception {
		
		
	}
	/**
	 * Method to test ContinentInCountries method
	 * @throws Exception
	 */
	@Test
	public void testReadMapWithInvalidContinentInCountries() throws Exception {
   MapReader mapReader = new MapReader();
   String path = "World_WithinvalidContinentinCountry.map";
   RiskMap riskMap = mapReader.readMap(path);
   System.out.println("testReadMapWithInvalidContinentInCountries"+riskMap);
   assertNull(riskMap);
   
	}
    /**
     * A method to test the functionality if countries have no neighbors 
     * @throws Exception
     */
	@Test
	public void testReadMapCountrieswithNoNeighbours() throws Exception {
   MapReader mapReader = new MapReader();
   String path = "World_CountryWithNoNeighbours.map";
   RiskMap riskMap = mapReader.readMap(path);
   System.out.println("testReadMapCountrieswithNoNeighbours"+riskMap);
   assertNull(riskMap);
   
	}
	/**
	 * Method to test Read map countries with asymmetric neighbors
	 * @throws Exception
	 */
	@Test
	public void testReadMapCountrieswithAsymmetricNeighbours() throws Exception {
   MapReader mapReader = new MapReader();
   String path = "World_AsymmetricNeighbours.map";
   RiskMap riskMap = mapReader.readMap(path);
   System.out.println("testReadMapCountrieswithAsymmetricNeighbours"+riskMap);
   assertNull(riskMap);
   
	}
	/**
	 * Method to test GetContinentofACountry 
	 */
	@Test
	public void testGetContinentOfACountry_validCountry() {
		System.out.println("\ntestGetContinentOfACountry_validCountry.........");
   MapReader mapReader = new MapReader();
   String path = "World.map";
   String country =  "Alberta";
   String continent = mapReader.getContinentOfACountry(country, path);
   assertEquals("North America", continent);
   assertNotNull(continent);
   
	}
   /**
    * Method to test GetContinentofACountry
    */
	@Test
	public void testGetContinentOfACountry_InvalidCountry() {
		System.out.println("\ntestGetContinentOfACountry_InvalidCountry.........");
   MapReader mapReader = new MapReader();
   String path = "World.map";
   String country =  "hello";
   String continent = mapReader.getContinentOfACountry(country, path);
   System.out.println("testGetContinentOfACountry_InvalidCountry"+continent);
   assertTrue(continent.isEmpty());
   
	}


}