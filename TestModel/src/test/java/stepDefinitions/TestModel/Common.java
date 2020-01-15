package stepDefinitions.TestModel;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.World;
import supportStatic.TestUtilities;

public class Common {

	private World world;

	public Common(World world) {
		this.world = world;
	}

	@Then("I check for errors")
	public void i_check_for_errors() {
		world.myTestWebElement.failTest();
	}

	@When("I am on the home page")
	public void i_am_on_the_Bank_page() throws InterruptedException {
		world.myTestWebElement.gotoURL("file:///c://Martin_Holmes_Files//Test HTML//Website//BrentfordBank.html");
	}

	@Then("I set {string} to {string}")
	public void i_set_field(String fieldName, String fieldValue) {
		world.myTestWebElement.setValue(fieldName, fieldValue);
	}
	
	@Then("I set {string} to {string} for the entry")
	public void i_set_field_for_entry(String fieldName, String fieldValue) {
		world.myTestWebElement.setValueCurrentEntry(fieldName, fieldValue);
	}

	@Then("I click {string}")
	public void i_click(String fieldName) {
		world.myTestWebElement.click(fieldName);
	}
	
	@Then("I click {string} followed by {string}")
	public void i_click_followed_by(String topMenu, String bottomMenu) {
		world.myTestWebElement.clickMenu(topMenu,bottomMenu);
	}

	@Then("I check {string} is {string}")
	public void i_check(String fieldName, String fieldValue) {
		world.myTestWebElement.checkFieldValue(fieldName, fieldValue);
	}

	@Then("I check {string} is {string} for entry")
	public void i_check_entry(String fieldName, String fieldValue) {
		//System.out.println(">>>> " + fieldName + " " + fieldValue);
		//fieldValue = "7°";
		//System.out.println(">>>> " + fieldName + " " + fieldValue);
		world.myTestWebElement.checkItemValue(fieldName, fieldValue);
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
	
	@Then("I zoom {int}")
	public void i_zoom(Integer int1) {
//		WebElement html = world.myTestWebElement.driver.findElement(By.tagName("html"));
//		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
//		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
//		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
//		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
		
//		WebElement html = world.myTestWebElement.driver.findElement(By.tagName("html"));
//		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
		JavascriptExecutor executor = (JavascriptExecutor)world.myTestWebElement.driver;
		executor.executeScript("document.body.style.zoom = '0.5'");
		
	}

	@Then("I go to the {string} Website")
	public void i_go_to_the_Website(String url) {
		world.myTestWebElement.gotoURL(url);
	}
	
	@Then("I check the {string} has the property {string} set to {string}")
	public void i_check_property_value(String fieldName, String fieldProperty, String fieldPropertyValue) {
		world.myTestWebElement.checkFieldProperty(fieldName, fieldProperty, fieldPropertyValue);
	}
	
	@Then("I add the comment {string}")
	public void i_check_property_value(String message) {
		System.out.println(message);
	}
	
	@Then("I have the test data in {string}")
	public void i_have_the_test_data_in(String string) {
	    world.myTestWebElement.myLoadData.insertData(string);
	}
	
	@Then("I remember for later the value of {string} as {string}")
	public void i_remember_for_later_the_value_of_as(String fieldName, String remember) {
	    world.myTestWebElement.setRememberFieldPair(fieldName, remember);
	}
	
	@Then("I focus on {string}")
	public void i_focus_on(String string) {
	    world.myTestWebElement.setCurrentFocus(string);
	}
	
	@Then("I focus on the whole page")
	public void i_focus_on_the_whole_page() {
		world.myTestWebElement.resetCurrentFocus();
	}


}
