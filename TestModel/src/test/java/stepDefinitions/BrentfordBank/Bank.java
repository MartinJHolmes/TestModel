package stepDefinitions.BrentfordBank;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.World;

public class Bank {

	private World world;

	public Bank(World world) {
		this.world = world;
	}

	@When("I am on the home page")
	public void i_am_on_the_Bank_page() throws InterruptedException {
		world.myTestWebElement.gotoURL("file:///c://Martin_Holmes_Files//Test HTML//Website//BrentfordBank.html");
	}

	@Then("I set {string} to {string}")
	public void i_set_field(String fieldName, String fieldValue)  {
		world.myTestWebElement.setValueTo(fieldName, fieldValue);
	}
	
	@Then("I set RadioButton {string} to {string}")
	public void i_set_radiobutton(String fieldName, String fieldValue) {
		world.myTestWebElement.setRadioButtonTo(fieldName, fieldValue);
	}

	@Then("I click {string}")
	public void i_click(String fieldName) {
		world.myTestWebElement.click(fieldName);
	}

	@Then("I check {string} is {string}")
	public void i_check(String fieldName, String fieldValue) {
		world.myTestWebElement.checkValue(fieldName, fieldValue);
	}

	@Then("page contains {string}")
	public void page_contains(String fieldValue) {
		world.myTestWebElement.checkPage(fieldValue);
	}
}
