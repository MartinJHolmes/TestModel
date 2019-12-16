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

	@Then("I see {string} on the page")
	public void i_see_on_the_page(String fieldValue) {
		System.out.println(fieldValue);
		world.myTestWebElement.checkPage(fieldValue);
	}
	@Then("chose product")
	public void chose_product() {
		WebElement myWE = world.myTestWebElement.driver.findElement(By.xpath("//div[@class='products']"));
		world.myTestWebElement.highlightWebElement(myWE, "blue");
		
		String Collection = "//div[@class='product']";

//		String Item = 	".//*[@src='images/savings.jpg']";
//		String Item = 	".//*[contains(text(),'Saving')]";
		String Item = 	".//a";
		
		String Value = "Mortgages";
		myWE = world.myTestWebElement.findCollectionItem(Collection, Item, Value);
		world.myTestWebElement.highlightWebElement(myWE, "green");
		myWE.findElement(By.xpath(".//a")).click();
		System.out.println(myWE.getTagName());
		TestUtilities.sleepTime(1000);
	}
	
	@Then("I try to click")
	public void i_try_to_click()  {
		world.myTestWebElement.clickAble("hello");
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

}
