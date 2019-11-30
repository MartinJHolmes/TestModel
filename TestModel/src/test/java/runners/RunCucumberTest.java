package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		//features = {"src/test/java/TestModel/Features/Bank4"},
		//features = {"classpath:Bank4"},
		//tags = {"@Bank4","@MUST"},
		features = {"classpath:TestModel"},
		glue = {"classpath:TestModel"},
		tags = {"@Bank4, @Bank5"}
		//plugin = {"pretty","html:C:/TestFolder"}
		//tags = {"@Bank4"}
		
		)
public class RunCucumberTest {
}