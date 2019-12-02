package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DetailsPagePoF {
	
	final WebDriver driver;
	 
	 @FindBy(how = How.XPATH, using = ".//*[@id='f_name']")
	 @CacheLookup
	 public WebElement firstName;
	 
	 public DetailsPagePoF(WebDriver driver) {
		 this.driver = driver;
	 }
	
	 
}
