package stepDefinitions.Bank4;

import io.cucumber.java.en.When;
import support.World;


public class Bank5 {
	//private Scenario scenario;
	private World world;
	
	public Bank5(World world) {
		this.world = world;
	}
	
	@When("I enter the date {string}")
	public void i_enter_the_date(String string) throws Exception {
		world.myTestWebElement.setValue("Date", "16031968");
	
		Thread.sleep(3000);
	}

}
