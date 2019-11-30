package TestModel.SDF.Bank4;

import Support.World;

import io.cucumber.java.en.When;


public class Bank5 {
	//private Scenario scenario;
	private World world;
	
	public Bank5(World world) {
		this.world = world;
	}
	
	@When("I enter the date {string}")
	public void i_enter_the_date(String string) throws Exception {
		world.myTestWebElement.setValueTo("Date", "16031968");
		world.myTestWebElement.trySomething();
		Thread.sleep(3000);
	}

}
