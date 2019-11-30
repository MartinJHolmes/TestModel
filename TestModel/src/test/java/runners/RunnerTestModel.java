package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;;


@RunWith(Cucumber.class) 
@CucumberOptions(
		//features = {"src/test/java/TestModel/Features/Bank4"},
		features = {"classpath:TestModel"},
		glue = {"classpath:TestModel"},
		//tags = {"@Bank4","@MUST"},
		tags = {"@Bank4"},
		plugin = {"pretty","html:C:/TestFolder"}
		//tags = {"@Bank4"}
		
		)

public class RunnerTestModel {

}

