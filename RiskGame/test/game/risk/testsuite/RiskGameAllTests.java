package game.risk.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import game.risk.model.MapReaderTest;
import game.risk.model.MapWriterTest;
import game.risk.model.PlayerTest;
import game.risk.model.validation.ValidateMapWriterTest;
import game.risk.util.PlayerUtilTest;

@RunWith(Suite.class)
@SuiteClasses({ValidateMapWriterTest.class,MapReaderTest.class,MapWriterTest.class,PlayerTest.class,PlayerUtilTest.class})
public class RiskGameAllTests {

}
