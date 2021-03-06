package game.risk.model.validation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;

import game.risk.gui.MapEditor;
import game.risk.model.MapReader;
import game.risk.model.MapWriter;
import game.risk.model.entities.RiskMap;
import game.risk.model.entities.Territory;
import game.risk.model.validation.ValidateMapWriter;

/**
 * Class to test Validation.java
 * 
 * @author Team
 *
 */
public class ValidateMapWriterTest {
	/**
	 * Method to test the Addlink method for adding a neighboring country which
	 * already exists as the neighboring country
	 * 
	 * @throws Exception
	 *             unchecked
	 */
	@Test
	public void testValidateAddLink_ForNeighbouringCountryAlreadyExist() throws Exception {
		String thisLine = "Alaska,30,46,North America,Northwest Territory,Alberta,Kamchatka";
		String country = "Alaska";
		String neighbouringCountry = "Alberta";

		ValidateMapWriter validateMapWriter = new ValidateMapWriter();
		boolean result = validateMapWriter.validateAddLink(country, neighbouringCountry, thisLine);

		assertFalse(result);
	}

	/**
	 * Method to test the AddLink method for resulting true if a neighboring country
	 * which already does not exist is added as the neighboring country
	 * 
	 * @throws Exception
	 *             unchecked
	 */
	@Test
	public void testValidateAddLink_ForNeighbouringCountryNotExist() throws Exception {
		String thisLine = "Alaska,30,46,North America,Northwest Territory,Alberta,Kamchatka";
		String country = "Alaska";
		String neighbouringCountry = "India";

		ValidateMapWriter validateMapWriter = new ValidateMapWriter();
		boolean result = validateMapWriter.validateAddLink(country, neighbouringCountry, thisLine);

		assertTrue(result);
	}

	/**
	 * Method to test Addlink Method for resulting false if a country is added
	 * itself as the neighboring country
	 * 
	 * @throws Exception
	 *             unchecked
	 */
	@Test
	public void testvalidateAddLinkAddingItselfFalse() throws Exception {
		String thisLine = "Alaska,30,46,North America,Northwest Territory,Alberta,Kamchatka";
		String country = "Alaska";
		String neighbouringCountry = "Alaska";

		ValidateMapWriter validateMapWriter = new ValidateMapWriter();
		boolean result = validateMapWriter.validateAddLinkAddingItself(country, neighbouringCountry, thisLine);

		assertFalse(result);
	}

	/**
	 * Method to test AddLink Method for resulting true if a country cannot add
	 * itself as its neighboring country
	 * 
	 * @throws Exception
	 *             unchecked
	 */
	@Test
	public void testvalidateAddLinkAddingItselfTrue() throws Exception {
		String thisLine = "Alaska,30,46,North America,Northwest Territory,Alberta,Kamchatka";
		String country = "Alaska";
		String neighbouringCountry = "India";

		ValidateMapWriter validateMapWriter = new ValidateMapWriter();
		boolean result = validateMapWriter.validateAddLinkAddingItself(country, neighbouringCountry, thisLine);

		assertTrue(result);
	}

	/**
	 * Method to test LinkToDelete for resulting true if the neighboring country is
	 * deleted from the file
	 * 
	 * @throws Exception
	 *             unchecked
	 */
	@Test
	public void testvalidateLinkToDeleteTrue() throws Exception {

		String countryLink = "Argentina";
		String mapFile = "World_TerritoryWithOneNeighbour.map";

		ValidateMapWriter validateMapWriter = new ValidateMapWriter();
		boolean result = validateMapWriter.validateLinkToDelete(countryLink, mapFile);

		assertFalse(result);
	}

	/**
	 * Method to test CheckTerritoriesBeforeDeletingContinents resulting false if
	 * territories are not checked before deleting continent
	 * 
	 * @throws Exception
	 *             unchecked
	 */
	@Test
	public void testCheckTerritoriesBeforeDeletingContinentFalse() throws Exception {

		String mapFile = "WorldDeleteInvalid.map";

		ValidateMapWriter validateMapWriter = new ValidateMapWriter();
		boolean result = validateMapWriter.checkTerritoriesBeforeDeletingContinent("hello", mapFile);

		assertTrue(result);
	}

	/**
	 * Method to test CheckAdjacentTerritoryLinkBeforeDelete resulting false if
	 * adjacent territory neighbor is not checked before delete.
	 * 
	 * @throws Exception
	 *             unchecked
	 */
	@Test
	public void testCheckAdjacentTerritoryLinkBeforeDeleteFalse() throws Exception {

		String mapFile = "WorldDeleteInvalid2.map";

		ValidateMapWriter validateMapWriter = new ValidateMapWriter();
		boolean result = validateMapWriter.checkAdjacentTerritoryLinkBeforeDelete("test1", mapFile);

		assertFalse(result);
	}

	/**
	 * Method to test whether the links between two territories can be deleted or
	 * not
	 * 
	 * @throws Exception
	 *             unchecked
	 */
	@Test
	public void testCheckTerritoryLinkBeforeDeleteLinkFalse() throws Exception {

		String mapFile = "WorldDeleteInvalid3.map";

		ValidateMapWriter validateMapWriter = new ValidateMapWriter();
		boolean result = validateMapWriter.checkTerritoryLinkBeforeDeleteLink("test1", "test2", mapFile);

		assertFalse(result);
	}

	/**
	 * Method to test list of countries are not having a continent for
	 * successful deletion of continent
	 * 
	 * @throws Exception read map exception
	 */
	@Test
	public void testCheckLinkedTerritoriesWithSelectedContinent_False() throws Exception {

		List<String> neighbouringCountries = new ArrayList<>();
		neighbouringCountries.add("Peru");

		ValidateMapWriter validateMapWriter = new ValidateMapWriter();
		boolean result = validateMapWriter.checkLinkedTerritoriesWithSelectedContinent("North America",
				neighbouringCountries, "World.map");

		assertFalse(result);
	}

	/**
	 * Method to test list of countries are having a continent for successful
	 * deletion of continent
	 * 
	 * @throws Exception read map exception
	 */
	@Test
	public void testCheckLinkedTerritoriesWithSelectedContinent_True() throws Exception {

		List<String> neighbouringCountries = new ArrayList<>();
		neighbouringCountries.add("Alaska");

		ValidateMapWriter validateMapWriter = new ValidateMapWriter();
		boolean result = validateMapWriter.checkLinkedTerritoriesWithSelectedContinent("North America",
				neighbouringCountries, "World.map");

		assertTrue(result);
	}
	
	/**
	 * Method to test list of countries are having a continent for successful
	 * deletion of continent
	 * 
	 * @throws Exception read map exception
	 */
	@Test
	public void testCheckLinkedTerritoriesWithContinent_True() throws Exception {

		List<String> neighbouringCountries = new ArrayList<>();
		neighbouringCountries.add("Alaska");

		ValidateMapWriter validateMapWriter = new ValidateMapWriter();
		boolean result = validateMapWriter.checkLinkedTerritoriesWithSelectedContinent("North America",
				neighbouringCountries, "World.map");

		assertTrue(result);
	}
}
