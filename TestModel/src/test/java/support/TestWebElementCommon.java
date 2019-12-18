package support;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import supportStatic.GlobalVariables;
import supportStatic.TestUtilities;
import supportStatic.WebElementMap;

public class TestWebElementCommon {

	public WebDriver driver;
	private WebElement currentEntry;
	
	public String myName = "Martin";
	private String myFieldName;
	private String myFieldValue;
	
	//private World world;

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public TestWebElementCommon() {
		//this.world = world;
	}
	
	
	// --------------------------------------------------------------------
	private static Boolean calledOutsideOfTestWebElement() {
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		if (stElements[3].getClassName().contains("TestWebElement")) {
			return false;
		}
		return true;
	}

	// --------------------------------------------------------------------
	private String returnValue(String value) {
		TestUtilities.printDebugMessage("STARTED");
		TestUtilities.printDebugMessage("FINISHED");
		return value;
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkItemValue(WebElement we, String fieldName, String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		WebElement returnWE = we.findElement(By.xpath(".//span[@class=(\"a-color-base\")]"));
		TestUtilities.printDebugMessage("<<< METHOD NOT WRITTEN >>>");
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkValue(String fieldName, String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		
		if (calledOutsideOfTestWebElement()) {
			myFieldName = returnValue(fieldName);
			myFieldValue = returnValue(fieldValue);
		}
		
		String fieldXPath = WebElementMap.getWebElementIdentifyBy(myFieldName);
		WebElement returnWE = driver.findElement(By.xpath(fieldXPath));
		highlightWebElement(returnWE, "green");
				
		switch (returnWE.getTagName()) {
		case "input":
			assertEquals(myFieldName + " did not match expected", returnWE.getAttribute("value"), myFieldValue);
			break;
			
		case "h1":
			assertEquals(myFieldName + " did not match expected", returnWE.getText(), myFieldValue);
			break;

		default:
			TestUtilities.printDebugMessage("<<< TAG NAME NOT RECOGNISED >>>");


		}
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkPage(String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		String xPathString = "//*[contains(.,'" + fieldValue + "')]";
		
		List<WebElement> elements = driver.findElements(By.xpath(xPathString));
		TestUtilities.printDebugMessage("<<< METHOD NOT COMPLETE >>>");
		
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void gotoURL(String URL) {
		TestUtilities.printDebugMessage("STARTED");
		driver.get(URL);
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void click(String fieldName) {
		TestUtilities.printDebugMessage("STARTED");
		WebElement myWE = driver.findElement(By.id(fieldName));
		highlightWebElement(myWE, "green");
		myWE.click();
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}
	
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void findEntry(String entryName,String fieldName, String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		String entryLocation = WebElementMap.getWebElementName(entryName);
		String fieldLocation = WebElementMap.getWebElementName(fieldName);
		
		if (entryLocation == null) {
			entryLocation = entryName;
		}
		if (fieldLocation == null) {
			fieldLocation = fieldName;
		}
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> entries = driver.findElements(By.xpath(entryLocation));
		Iterator<WebElement> iter = entries.iterator();
		while (iter.hasNext()) {
			WebElement entry = iter.next();
			
			// highlight each entry
			highlightWebElement(entry, "blue");
			
			try {
				// find the field
				WebElement element = entry.findElement(By.xpath(fieldLocation));
				highlightWebElement(element, "blue");
				
				// check the value is correct
				String tagName = element.getTagName();
				String elementValue = null;
				switch (tagName) {
				case "a":
				case "span":
				case "div":
					elementValue = element.getText();
					break;
				default:
					TestUtilities.printDebugMessage("<<< TAG NAME NOT RECOGNISED >>>  " + tagName);
				}
				
				if (elementValue.equals(fieldValue)) {
					highlightWebElement(element, "green");
					//save entry as current
					currentEntry = entry;
				} else {
					highlightWebElement(element, "red");
				}
			} catch (Exception e) {
				TestUtilities.printDebugMessage("<<< METHOD NOT COMPLETE >>>");
			}
		}
		TestUtilities.sleepTime();	
		TestUtilities.printDebugMessage("FINISHED");
	}
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void clickEntry() {
		TestUtilities.printDebugMessage("STARTED");

		currentEntry.findElement(By.xpath(".//a")).click();;
		TestUtilities.sleepTime();	
		TestUtilities.printDebugMessage("FINISHED");
		
	}
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void setValue(String fieldName, String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		// transform input values if special characters are used
		fieldName = returnValue(fieldName);
		fieldValue = returnValue(fieldValue);
		
		setValue(null,fieldName,fieldValue);
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void setValueCurrentEntry(String fieldName, String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		// transform input values if special characters are used
		fieldName = returnValue(fieldName);
		fieldValue = returnValue(fieldValue);
		
		if (currentEntry==null) {
			TestUtilities.printDebugMessage("<<< ENTRY WAS NOT FOUND BEFORE CALLING THIS METHOD >>>");
		}
		setValue(currentEntry,fieldName,fieldValue);
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}
	
	// ---------------------------------------------------------------------
	private void setValue(WebElement currentEntry,String fieldName, String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		TestUtilities.printDebugMessage(" fieldValue = " + fieldValue);
		// initialise local values

		List<WebElement> elements = findElements(fieldName);

		
		
		
		String tagName = elements.get(0).getTagName();
		switch(tagName) {
		case "input":
			String typeName = elements.get(0).getAttribute("type");
			
			switch(typeName) {
			case "radio":
				
				System.out.println(" field " + fieldName + "  testElements = " + elements.size());
				Iterator<WebElement> iter = elements.iterator();
				while (iter.hasNext()) {
					WebElement entry = iter.next();
					highlightWebElement(entry,"blue");
					if (entry.getAttribute("value").equals(fieldValue)) {
						highlightWebElement(entry,"green");
						entry.click();
					} else {
						highlightWebElement(entry,"red");
					}
				}
				
				break;
			case "text":
			case "password":
			case "date":
				elements.get(0).clear();
				elements.get(0).sendKeys(fieldValue);
				highlightWebElement(elements.get(0),"green");
				break;
			default:
				TestUtilities.printDebugMessage("<<< TYPE NAME NOT RECOGNISED >>>");
			}
			break;
		default:
			TestUtilities.printDebugMessage("<<< TAG NAME NOT RECOGNISED >>>");	
		}
		TestUtilities.printDebugMessage("FINISHED");
	}
	
	// --------------------------------------------------------------------
	private List<WebElement> findElements(String fieldName) {
		TestUtilities.printDebugMessage("STARTED");
		WebElement currentElement = null;
		List<WebElement> entries = new ArrayList<>();
		String fieldLocation = WebElementMap.getWebElementName(fieldName);
		
		System.out.println(" field location = " + fieldLocation);
		
		if(fieldLocation!=null) {
			entries = driver.findElements(By.xpath(fieldLocation));
			Iterator<WebElement> iter = entries.iterator();
			while (iter.hasNext()) {
				WebElement entry = iter.next();
				
				// highlight each entry
				highlightWebElement(entry, "blue");
			}	
			//return entries;
				

		} else {
			fieldLocation = fieldName;
			String firstChar = fieldLocation.charAt(0) + "";
			if (firstChar.equals("/")) {
				currentElement = driver.findElement(By.xpath(fieldLocation));
				entries.add(currentElement);
				highlightWebElement(currentElement, "blue");
				//TestUtilities.sleepTime();
			} else {
				currentElement = driver.findElement(By.xpath("//*[@id=(" + "//label[text()[(normalize-space(.)='" + fieldLocation + "')]]" + "/@for)]"));
				entries.add(currentElement);
				highlightWebElement(currentElement, "blue");
				//TestUtilities.sleepTime();
			}
			
			//// TODO
			//// By.xpath("//*[@id=(" + "//label[text()[(normalize-space(.)='" + fieldName + "')]]" + "/@for)]"));
		}	
		TestUtilities.printDebugMessage("FINISHED");
		return entries;
	}

	// -------------------------------------------------------------
	private void highlightWebElement(WebElement myTest, String highlightColour) {
		TestUtilities.printDebugMessage("STARTED");

		if (GlobalVariables.highlightWebElementRequired == true) {
			// System.out.println(myTest.getAttribute("id"));
			int w = myTest.getSize().getWidth() + 8;
			int h = myTest.getSize().getHeight() + 8;
			int t = myTest.getLocation().getY() - 6;
			int l = myTest.getLocation().getX() - 8;
			// System.out.println(myTest.getAttribute("id") + "Width = " + w);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(" " + "var btn=document.createElement('DIV');" + "btn.id = 'Hello';"
					+ "btn.style = 'border:3px solid " + highlightColour + ";" + " position: absolute;" + "width: " + w
					//+ "px; " + "height:" + h + "px; " + "top:" + t + "px; " + "left:" + l + "px';"
					+ "px; " + "height:" + h + "px; " + "top:" + t + "px; " + "left:" + l + "px; z-index: 1000';"
					+ "document.body.appendChild(btn);");
			TestUtilities.sleepTime(GlobalVariables.highlightTime);
			js.executeScript(
					"var element = document.getElementById('Hello');" + "element.parentNode.removeChild(element);");

		}
		TestUtilities.printDebugMessage("FINISHED");
	}
	
}
