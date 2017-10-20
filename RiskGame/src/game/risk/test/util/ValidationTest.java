package game.risk.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import game.risk.gui.MapEditor;
import game.risk.util.MapReader;
import game.risk.util.MapWriter;
import game.risk.util.RiskMap;
import game.risk.util.Validation;

/**
 * Class to test Validation.java
 * 
 * @author Team
 *
 */
public class ValidationTest {
	/**
	 * Method to test the Addlink method for adding a neighboring country which
	 * already exists as the neighboring country
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidateAddLink_ForNeighbouringCountryAlreadyExist() throws Exception {
		String thisLine = "Alaska,30,46,North America,Northwest Territory,Alberta,Kamchatka";
		String country = "Alaska";
		String neighbouringCountry = "Alberta";

		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.validateAddLink(country, neighbouringCountry, thisLine);

		assertFalse(result);
	}

	/**
	 * Method to test the AddLink method for resulting true if a neighboring country
	 * which already does not exist is added as the neighboring country
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidateAddLink_ForNeighbouringCountryNotExist() throws Exception {
		String thisLine = "Alaska,30,46,North America,Northwest Territory,Alberta,Kamchatka";
		String country = "Alaska";
		String neighbouringCountry = "India";

		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.validateAddLink(country, neighbouringCountry, thisLine);

		assertTrue(result);
	}

	/**
	 * Method to test Addlink Method for resulting false if a country is added
	 * itself as the neighboring country
	 * 
	 * @throws Exception
	 */
	@Test
	public void testvalidateAddLinkAddingItselfFalse() throws Exception {
		String thisLine = "Alaska,30,46,North America,Northwest Territory,Alberta,Kamchatka";
		String country = "Alaska";
		String neighbouringCountry = "Alaska";

		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.validateAddLinkAddingItself(country, neighbouringCountry, thisLine);

		assertFalse(result);
	}

	/**
	 * Method to test AddLink Method for resulting true if a country cannot add
	 * itself as its neighboring country
	 * 
	 * @throws Exception
	 */
	@Test
	public void testvalidateAddLinkAddingItselfTrue() throws Exception {
		String thisLine = "Alaska,30,46,North America,Northwest Territory,Alberta,Kamchatka";
		String country = "Alaska";
		String neighbouringCountry = "India";

		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.validateAddLinkAddingItself(country, neighbouringCountry, thisLine);

		assertTrue(result);
	}

	/**
	 * Method to test LinkToDelete for resulting true if the neighboring country is
	 * deleted from the file
	 * 
	 * @throws Exception
	 */
	@Test
	public void testvalidateLinkToDeleteTrue() throws Exception {

		String countryLink = "Argentina";
		String mapFile = "World_TerritoryWithOneNeighbour.map";

		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.validateLinkToDelete(countryLink, mapFile);

		assertFalse(result);
	}

	/**
	 * Method to test CheckTerritoriesBeforeDeletingContinents resulting false if
	 * territories are not checked before deleeting continent
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCheckTerritoriesBeforeDeletingContinentFalse() throws Exception {

		String mapFile = "WorldDeleteInvalid.map";

		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.checkTerritoriesBeforeDeletingContinent("hello", mapFile);

		assertFalse(result);
	}

	/**
	 * Method to test CheckAdjacentTerritoryLinkBeforeDelete resulting false if
	 * adjacent territory neighbor is not checked before delete.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCheckAdjacentTerritoryLinkBeforeDeleteFalse() throws Exception {

		String mapFile = "WorldDeleteInvalid2.map";

		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.checkAdjacentTerritoryLinkBeforeDelete("test1", mapFile);

		assertFalse(result);
	}
	
	@Test
	public void testCheckTerritoryLinkBeforeDeleteLinkFalse() throws Exception {

		String mapFile = "WorldDeleteInvalid3.map";

		Validation validateMapWriter = new Validation();
		boolean result = validateMapWriter.checkTerritoryLinkBeforeDeleteLink("test1","test2", mapFile);

		assertFalse(result);
	}

}
