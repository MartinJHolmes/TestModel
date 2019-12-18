package stepDefinitions.BrentfordBank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.World;
import supportStatic.TestUtilities;

public class Common {

	private World world;

	public Common(World world) {
		this.world = world;
	}

	@When("I am on the home page")
	public void i_am_on_the_Bank_page() throws InterruptedException {
		world.myTestWebElement.gotoURL("file:///c://Martin_Holmes_Files//Test HTML//Website//BrentfordBank.html");
	}

	@Then("I set {string} to {string}")
	public void i_set_field(String fieldName, String fieldValue)  {
		world.myTestWebElement.setValue(fieldName, fieldValue);
	}

	@Then("I click {string}")
	public void i_click(String fieldName) {
		world.myTestWebElement.click(fieldName);
	}

	@Then("I check {string} is {string}")
	public void i_check(String fieldName, String fieldValue) {
		world.myTestWebElement.checkValue(fieldName, fieldValue);
	}

	@Then("I see {string} on the page")
	public void i_see_on_the_page(String fieldValue) {
		world.myTestWebElement.checkPage(fieldValue);
	}
	
	@Then("I try something different")
	public void i_try_something_different() {
	    // Write code here that turns the phrase above into concrete actions
	    world.myTestWebElement.doSomething();
	}
	
	@Then("I find the entry where {string} is {string}")
	public void i_find_the_entry_where_is(String string, String string2) {
	    System.out.println("I find the entry.. IS NOT COMPLETE");
	}
	
	@Then("I find the {string} where {string} is {string}")
	public void i_find_the_where_is(String string, String string2, String string3) {
		world.myTestWebElement.findEntry(string, string2, string3);
	}
	
	@Then("I click the entry")
	public void i_click_the_entry() {
	    world.myTestWebElement.clickEntry();
	}
	
	@Then("I sleep {int} ms")
	public void i_sleep_ms(Integer int1) {
	    TestUtilities.sleepTime(int1);
	}
	
	@Then("I test java method")
	public void i_test_java_method() {
	    // ONLY USED FOR TESTING JAVA METHODS
		world.myTestWebElement.gotoURL("https://www.bbc.co.uk/weather/2643743");
		TestUtilities.sleepTime(2000);
	    world.myTestWebElement.findEntry("//li[contains(@class,'wr-day')]", ".//div[contains(@class,'wr-day__title')]", "Sat 21st");
	}

}
