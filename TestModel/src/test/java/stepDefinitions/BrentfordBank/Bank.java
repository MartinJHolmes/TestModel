package stepDefinitions.BrentfordBank;

import io.cucumber.java.en.When;
import support.World;

public class Bank {
	
	private World world;

	public Bank(World world) {
		this.world = world;
	}

	@When("I successfully logon")
	public void i_successfully_logon() {
		world.myTestWebElement.gotoURL("file:///c://Martin_Holmes_Files//Test HTML//Website//BrentfordBank.html");
		world.myTestWebElement.setValue("Username", "holmesm");
		world.myTestWebElement.setValue("Password", "Password");
		world.myTestWebElement.click("Logon");
	}

}
