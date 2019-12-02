package stepDefinitions.Amazon;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.World;


public class AmazonBookSearch {
	
	private World world;
	
	public AmazonBookSearch(World world) {
		this.world = world;
	}
	
	@When("I am on the Amazon Page")
	public void i_am_on_the_Bank_page() {
		world.myTestWebElement.gotoURL("http://www.amazon.co.uk");
	}
	
	@Then("I search for Child44 book")
	public void i_search_for_Child44_book() throws InterruptedException {
		Thread.sleep(10000);
		world.myTestWebElement.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		//Thread.sleep(3000);
		
		
		WebElement newChild = world.myTestWebElement.driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
		newChild.sendKeys("child44 book");
		newChild = world.myTestWebElement.driver.findElement(By.xpath("//input[@class=(\"nav-input\")]"));
		newChild.click();
				
	}
	
	@Then("I select the 1987 Item")
	public void i_select_the_Item() throws InterruptedException {
		String Collection = "//div[@class=(\"s-expand-height s-include-content-margin s-border-bottom\")]";
		String Item = 	".//*[@class=\"a-size-base a-color-secondary a-text-normal\"]";
		String Value = "1987";
		WebElement eItem = world.myTestWebElement.findCollectionItem(Collection, Item, Value);
		Thread.sleep(3000);
		eItem.click();
//		System.out.println("The class = " + eItem.getAttribute("class"));
		Thread.sleep(5000);
	}
}
