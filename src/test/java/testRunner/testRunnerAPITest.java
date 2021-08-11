package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @description: This class acts as an interlink between cucumber feature files and step definition classes 
 * @author sreeramvennapusa
 */


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/main/java/feature/RestScenarios.feature",
		glue= {"stepDefination.rest"},
		//tags = "@RestAPIRegression",
		plugin = {"pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"}
		)
public class testRunnerAPITest {
}