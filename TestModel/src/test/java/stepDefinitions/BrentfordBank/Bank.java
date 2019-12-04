package stepDefinitions.BrentfordBank;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.World;
import supportStatic.GlobalVariables;

public class Bank {

	private World world;

	public Bank(World world) {
		this.world = world;
	}

	@When("I am on the home page")
	public void i_am_on_the_Bank_page() throws InterruptedException {

		world.myTestWebElement.gotoURL("file:///c://Martin_Holmes_Files//Test HTML//Website//BrentfordBank.html");

		Thread.sleep(1000);

	}

	@Then("I set {string} to {string}")
	public void i_set_field(String fieldName, String fieldValue) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		world.myTestWebElement.setValueTo(fieldName, fieldValue);
		Thread.sleep(1000);
	}

	@Then("I click {string}")
	public void i_click(String fieldName) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		world.myTestWebElement.click(fieldName);
		Thread.sleep(1000);
	}

	@Then("I check {string} is {string}")
	public void i_check(String fieldName, String fieldValue) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions

		world.myTestWebElement.checkValue(fieldName, fieldValue);

		Thread.sleep(1000);
	}

	@Then("page contains {string}")
	public void page_contains(String fieldValue) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		world.myTestWebElement.checkPage(fieldValue);

	}
}
