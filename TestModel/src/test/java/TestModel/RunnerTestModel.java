package TestModel;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;;




@RunWith(Cucumber.class) 
@CucumberOptions(
		//features = {"src/test/java/TestModel/Features/Bank4"},
		//features = {"classpath:Bank4"},
		//tags = {"@Bank4","@MUST"},
		tags = {"@Bank4, @Bank5"},
		plugin = {"pretty","html:C:/TestFolder"}
		//tags = {"@Bank4"}
		
		)

public class RunnerTestModel {

}

