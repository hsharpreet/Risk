package game.risk.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import game.risk.model.GameReaderTest;
import game.risk.model.GameWriterTest;
import game.risk.model.MapReaderTest;
import game.risk.model.MapWriterTest;
import game.risk.model.TournamentModelTest;
import game.risk.model.entities.PlayerTest;
import game.risk.model.validation.ValidateMapWriterTest;
import game.risk.util.PlayerUtilTest;


/**
 * Suite class to run all test cases of risk game
 * @author amanp
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ValidateMapWriterTest.class,MapReaderTest.class,MapWriterTest.class,PlayerTest.class,PlayerUtilTest.class
	,GameWriterTest.class,GameReaderTest.class,TournamentModelTest.class})
public class RiskGameAllTests {

}
