package support;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import supportStatic.GlobalVariables;
import supportStatic.TestUtilities;



public class TestWebElement {
	
	
	public WebDriver driver;
	public String myName = "Martin";

	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public TestWebElement() {
		// Get Classes passed to the class
		// ===========================
		//this.driver = driver;
		System.out.println("TestWebElementNS constructor");
		

	}
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void gotoURL(String URL) {
		driver.get(URL);
		
		
	}
	
	public WebElement findCollectionItem(String Collection, String Item, String Value) {
		WebElement aWe = null;
		
		driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS) ;
		List<WebElement> parent = driver.findElements(By.xpath(Collection));
		Iterator<WebElement> iter = parent.iterator();
		while(iter.hasNext()) {
		    WebElement we = iter.next();
		    highlightWebElement(we, "red");
		    try {
		    WebElement child = we.findElement(By.xpath(Item));
		    highlightWebElement(child, "yellow");
		    	System.out.println("The years is " + child.getText());
		    	if(child.getText().equals(Value)) {
		    		System.out.println("ITEM FOUND");
		    		highlightWebElement(child, "blue");
		    		aWe = we;
		    		return we;
		    	}
		    } catch (Exception e) {
		    	System.out.println("Did not find");
		    }
		    highlightWebElement(we, "red");
		}
		return aWe;
		    
	}
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void setValueTo(String fieldName, String fieldValue) {
		
	    WebElement child = null;
	    
	    
	    // <<<< TODO
	    // Replaces _* with "Special"
	    // Needs to lookup in Load or Run Data
	    Character c1 = '_'; 
	    Character c2 = fieldValue.charAt(0);
	    
	    if(c1.equals(c2)) {
	    	fieldValue = "Special";
	    	// TODO get actual vale
	    }
	    
	    // >>>> 
 
		
		// Check if Label exists for fieldName
		try {
			child = driver.findElement(
                    By.xpath("//*[@id=(" + "//label[text()[(normalize-space(.)='" + fieldName + "')]]" + "/@for)]"));
			
			
			child.clear();
			child.sendKeys(fieldValue);
		} catch (Exception e) {
			// Check if field with that name exists
			child = driver.findElement(By.id(fieldName));
			child.clear();
			child.sendKeys(fieldValue);
			
		}

		highlightWebElement(child, "blue");
    

	}
	
	//####################################################################
	protected void highlightWebElement(WebElement myWebElement, String highlightColour) {

		
		if (GlobalVariables.highlightWebElementRequired == true) {
	
		
		try {
			for (int i=0;i<2;i++) {
				if (driver instanceof org.openqa.selenium.JavascriptExecutor) {
			        ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid " + highlightColour + "';", myWebElement);
				}
				
				TestUtilities.sleepTime(GlobalVariables.highlightTime);
				if (driver instanceof org.openqa.selenium.JavascriptExecutor) {
			        ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("arguments[0].style.border='1px solid green'", myWebElement);
			       
				}
			}
		} catch (Exception e) {
			//System.out.println("Could not highlight");
		}
		
		
		try {
		
				if (driver instanceof org.openqa.selenium.JavascriptExecutor) {
			        ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("arguments[0].style.border='1px solid green'", myWebElement);
			       
				}
		
		} catch (Exception e) {
			System.out.println("Could not highlight");
		}

	}
	}

}
