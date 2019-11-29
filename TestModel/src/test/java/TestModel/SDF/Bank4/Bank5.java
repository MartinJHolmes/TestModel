package TestModel.SDF.Bank4;

import TestModel.Support.TestWebElement;
import cucumber.api.Scenario;
import cucumber.api.java.en.When;

public class Bank5 {
	//private Scenario scenario;
	
	@When("I enter the date {string}")
	public void i_enter_the_date(String string) throws Exception {
		TestWebElement.setValueTo("Date", "16031968");
		TestWebElement.trySomething();
		Thread.sleep(3000);
	}

}
