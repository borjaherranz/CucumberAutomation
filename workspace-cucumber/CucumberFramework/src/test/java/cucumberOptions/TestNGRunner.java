package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/features",
		glue= "stepDefinitions",
		plugin = {
			"pretty",
			"json:target/cucumber.json",
			"junit:target/cucumber-junit.xml"
		},
		tags = "not @Solution"
		)
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
