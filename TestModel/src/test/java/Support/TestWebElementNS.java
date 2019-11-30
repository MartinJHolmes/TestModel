package Support;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import TestModel.SDF.GlobalVariables;

public class TestWebElementNS {
	
	
	public WebDriver driver;
	public String myName = "Martin";

	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public TestWebElementNS() {
		// Get Classes passed to the class
		// ===========================
		//this.driver = driver;
		System.out.println("TestWebElementNS constructor");
		

	}
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void trySomething() throws Exception {
		WebElement myElem = driver.findElement(By.id("aDate"));
		String eMessage = myElem.getTagName();
		
		System.out.println("The class = " + eMessage);
		
		//TakesScreenshot srcShot = ((TakesScreenshot)myElem);
		//File SrcFile= srcShot.getScreenshotAs(OutputType.FILE);
		
		//TestWebElement.takeSnapShot("c://Martin_Holmes_Files//Test HTML//screen.png");
	}
		
	public void takeSnapShot(String fileWithPath) throws Exception{
		//public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File(fileWithPath);

                //Copy file at destination

                FileUtils.copyFile(SrcFile, DestFile);

    }
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void gotoURL(String URL) {
		driver.get(URL);
		
		
	}
	
	public WebElement findCollectionItem(String Collection, String Item, String Value) {
		WebElement aWe = null;
//		String Collection = "//div[@class=(\"s-expand-height s-include-content-margin s-border-bottom\")]";
//		String Item = 	".//*[@class=\"a-size-base a-color-secondary a-text-normal\"]";
//		String Value = "2015";
		
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
        //WebDriver driver = RunLabelSearchTest.driver;
        
		//System.out.println("The value = '" + fieldName + "'");
				
		
		//WebElement myElem = driver.findElement(By.id(fieldName));
		//myElem.sendKeys(fieldValue);
		
		// Check if Label exists for fieldName
		//System.out.println("//*[@id=(" + "//label[text()[(normalize-space(.)='" + fieldName + "')]]" + "/@for)]");
		try {
			child = driver.findElement(
                    By.xpath("//*[@id=(" + "//label[text()[(normalize-space(.)='" + fieldName + "')]]" + "/@for)]"));
			
			//System.out.println("Found by Label");
			child.clear();
			child.sendKeys(fieldValue);
		} catch (Exception e) {
			// Check if field with that name exists
			child = driver.findElement(By.id(fieldName));
			child.clear();
			child.sendKeys(fieldValue);
			//System.out.println("Found by Name");
		}
		// Check if field with that name exists
		// Check if fieldName in fieldmap
		// Else
		highlightWebElement(child, "blue");
    

	}
	
	//####################################################################
	protected void highlightWebElement(WebElement myWebElement, String highlightColour) {
//		int remSleep = GlobalVariables.sleepBetweenSteps;
//		GlobalVariables.sleepBetweenSteps = 200;
		
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
