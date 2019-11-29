package TestModel.SDF.Amazon;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import TestModel.Environ.GlobalVariables;
import TestModel.Support.TestWebElement;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AmazonBookSearch {
	@When("I am on the Amazon Page")
	public void i_am_on_the_Bank_page() {
		TestWebElement.gotoURL("http://www.amazon.co.uk");
	}
	
	@Then("I search for Child44 book")
	public void i_search_for_Child44_book() throws InterruptedException {
		Thread.sleep(10000);
		TestWebElement.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		//Thread.sleep(3000);
		
		
		WebElement newChild = TestWebElement.driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
		newChild.sendKeys("child44 book");
		newChild = TestWebElement.driver.findElement(By.xpath("//input[@class=(\"nav-input\")]"));
		newChild.click();
				
	}
	
	@Then("I select the 1987 Item")
	public void i_select_the_Item() throws InterruptedException {
		String Collection = "//div[@class=(\"s-expand-height s-include-content-margin s-border-bottom\")]";
		String Item = 	".//*[@class=\"a-size-base a-color-secondary a-text-normal\"]";
		String Value = "1987";
		WebElement eItem = TestWebElement.findCollectionItem(Collection, Item, Value);
		Thread.sleep(3000);
		eItem.click();
//		System.out.println("The class = " + eItem.getAttribute("class"));
		Thread.sleep(5000);
	}
}