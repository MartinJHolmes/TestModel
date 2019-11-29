package TestModel;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class) 
@CucumberOptions(
		//features = {"src/test/java/TestModel/Features/Bank4"},
		//features = {"classpath:Bank4"},
		//tags = {"@Bank4","@MUST"},
		tags = {"@Bank4-Basic"},
		plugin = {"pretty","html:C:/TestFolder"}
		//tags = {"@Bank4"}
		
		)

public class RunTestModel02 {

}

