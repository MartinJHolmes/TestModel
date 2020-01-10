package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;;


@RunWith(Cucumber.class) 
@CucumberOptions(
		//features = {"src/test/java/TestModel/Features/Bank4"},
		
		
		features = {"classpath:TestModel"},
		//glue = {"classpath:TestModel"},
		glue = {"classpath:stepDefinitions"},
		//tags = {"@BB01-01a or @BB01-01"},
		tags = "@Test",
		plugin = {"pretty","html:C:/TestFolder"}
		//tags = {"@Bank4"}
		
		)

public class RunnerTestModelTest {

}

