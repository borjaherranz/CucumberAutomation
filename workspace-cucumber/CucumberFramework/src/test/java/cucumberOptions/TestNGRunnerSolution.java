package cucumberOptions;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/features",
		glue= "stepDefinitions",
		tags = "@PlaceOrder or @OffersPage",
		plugin= {"html:target/cucumber.html",
				"json:target/cucumber.json",
				"junit:target/cucumber-junit.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:target/failed_scenarios.txt"
				},
		monochrome=true
		)
public class TestNGRunnerSolution extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
