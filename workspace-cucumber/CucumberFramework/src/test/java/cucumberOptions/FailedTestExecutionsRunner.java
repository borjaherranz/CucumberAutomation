package cucumberOptions;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "@target/failed_scenarios.txt",
		glue= "stepDefinitions",
		plugin= {"html:target/cucumber.html",
				"json:target/cucumber.json",
				"junit:target/cucumber-junit.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				},
		monochrome=true
		)
public class FailedTestExecutionsRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
