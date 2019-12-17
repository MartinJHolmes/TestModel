package support;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public TestWebElementCommon() {

	}
	
	
	
	
	
	
	
	
	
	

	// --------------------------------------------------------------------
	private static Boolean calledOutsideOfTestWebElement() {
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		if (stElements[3].getClassName().contains("TestWebElement")) {
			return false;
		}
		return true;
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public String returnValue(String value) {
		return value;
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkValue(WebElement we, String fieldName, String fieldValue) {

		WebElement returnWE = we.findElement(By.xpath(".//span[@class=(\"a-color-base\")]"));
		System.out.println("checkValue: METHOD NOT WRITTEN");
		TestUtilities.sleepTime();
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkValue(String fieldName, String fieldValue) {
		//System.out.println("Caller Class = " + getCallerClassName());
		if (calledOutsideOfTestWebElement()) {
			myFieldName = returnValue(fieldName);
			myFieldValue = returnValue(fieldValue);
		}
		System.out.println("checkValue: myFieldName = " + myFieldName);
		String fieldXPath = WebElementMap.getWebElementIdentifyBy(myFieldName);
		WebElement returnWE = driver.findElement(By.xpath(fieldXPath));
		highlightWebElement(returnWE, "green");
		
		//System.out.println("Tag Name = " + returnWE.getTagName());
		
		switch (returnWE.getTagName()) {
		case "input":
			//System.out.println("Input reached");
			assertEquals(myFieldName + " did not match expected", returnWE.getAttribute("value"), myFieldValue);
			break;
			
		case "h1":
			assertEquals(myFieldName + " did not match expected", returnWE.getText(), myFieldValue);
			break;

		default:
			System.out.println("*** Default Reached ***");


		}
		TestUtilities.sleepTime();
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkPage(String fieldValue) {
		String xPathString = "//*[contains(.,'" + fieldValue + "')]";
		//System.out.println("XPATH = " + xPathString);
		//TestUtilities.sleepTime(2000);
		List<WebElement> elements = driver.findElements(By.xpath(xPathString));
		//System.out.println("Found number of times = " + elements.size());
		//assertTrue(fieldValue + " was not found", element.getText().contains(fieldValue));
		TestUtilities.sleepTime();
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void gotoURL(String URL) {
		driver.get(URL);
		TestUtilities.sleepTime();
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void click(String fieldName) {
		WebElement myWE = driver.findElement(By.id(fieldName));
		highlightWebElement(myWE, "green");
		myWE.click();
		TestUtilities.sleepTime();
	}

	// <<<<<<<<<<<<<< TO BE REMOVED
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void clickAble99(String fieldName) {
		// WebElement myWE =
		// driver.findElement(By.xpath("//img[@src='images/savings.jpg']"));
		WebElement myWE = driver.findElement(By.xpath("//div[@class='product']"));

		highlightWebElement(myWE, "green");
		myWE.click();
		TestUtilities.sleepTime();
	}
	// >>>>>>>>>>>>>>
	
	
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void findEntry(String entryName,String fieldName, String fieldValue) {
		String entryLocation = WebElementMap.getWebElementName(entryName);
		String fieldLocation = WebElementMap.getWebElementName(fieldName);
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> entries = driver.findElements(By.xpath(entryLocation));
		Iterator<WebElement> iter = entries.iterator();
		while (iter.hasNext()) {
			WebElement entry = iter.next();
			
			// highlight each entry
			highlightWebElement(entry, "blue");
			System.out.println("BLUE >>>>>>>>>>>>>>");
			
			try {
				// find the field
				WebElement element = entry.findElement(By.xpath(fieldLocation));
				highlightWebElement(element, "blue");
				
				// check the value is correct
				String tagName = element.getTagName();
				String elementValue = null;
				switch (tagName) {
				case "a":
					elementValue = element.getText();
					break;
				default:
					System.out.println("FIND ENTRY - SWITCH VALUE NOT RECOGNISED");
				}
				
				if (elementValue.equals(fieldValue)) {
					highlightWebElement(element, "green");
					//save entry as current
					currentEntry = entry;
				} else {
					highlightWebElement(element, "red");
				}
			} catch (Exception e) {
				System.out.println("findCollection: Did not find");
			}
		}
		TestUtilities.sleepTime();		
	}
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void clickEntry() {
		System.out.println("TAG NAME = " + currentEntry.getTagName());
		currentEntry.findElement(By.xpath(".//a")).click();;
		TestUtilities.sleepTime();	
		
	}
	
// <<<<<<<<<<<<<< TO BE REMOVED
	public WebElement findCollectionItem99(String Collection, String Item, String Value) {
		WebElement aWe = null;

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> parent = driver.findElements(By.xpath(Collection));
		Iterator<WebElement> iter = parent.iterator();
		while (iter.hasNext()) {
			WebElement we = iter.next();
			highlightWebElement(we, "blue");
			try {
				WebElement child = we.findElement(By.xpath(Item));
				highlightWebElement(child, "blue");
				System.out.println("findCollection " + child.getText());
				if (child.getText().equals(Value)) {
					highlightWebElement(child, "green");
					aWe = we;
					return we;
				}
			} catch (Exception e) {
				System.out.println("findCollection: Did not find");
			}
			highlightWebElement(we, "red");
		}
		TestUtilities.sleepTime();
		return aWe;
	}
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void setValue(String fieldName, String fieldValue) {
		// transform input values if special characters are used
		fieldName = returnValue(fieldName);
		fieldValue = returnValue(fieldValue);
		
		setValue(null,fieldName,fieldValue);
		TestUtilities.sleepTime();
	}
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void setValueCurrentEntry(String fieldName, String fieldValue) {
		// transform input values if special characters are used
		fieldName = returnValue(fieldName);
		fieldValue = returnValue(fieldValue);
		
		if (currentEntry==null) {
			System.out.println("ERROR");
		}
		setValue(currentEntry,fieldName,fieldValue);
		TestUtilities.sleepTime();
	}
	
	// ---------------------------------------------------------------------
	private void setValue(WebElement currentEntry,String fieldName, String fieldValue) {
		// initialise local values
		WebElement currentElement = null;
		
		currentElement = findElement(fieldName);
		
		String tagName = currentElement.getTagName();
		switch(tagName) {
		case "input":
			String typeName = currentElement.getAttribute("type");
			System.out.println("TYPE = " + typeName);
			switch(typeName) {
			case "radio":
				String fieldLocation = WebElementMap.getWebElementName(fieldName);
				if (fieldLocation == null) {
					fieldLocation = fieldName;
				}
				String xPath = "(" + fieldLocation + ")[@value='" + fieldValue + "']";
				System.out.println("XPATH = " + xPath);
				currentElement = driver.findElement(By.xpath(xPath));
				highlightWebElement(currentElement,"green");
				currentElement.click();
				
				break;
			case "text":
			case "password":
			case "date":
				currentElement.clear();
				currentElement.sendKeys(fieldValue);
				highlightWebElement(currentElement,"green");
				break;
			default:
				System.out.println("TYPE NOT RECOGNISED " + typeName);
			}
			break;
		default:
			System.out.println("SET VALUE = Tag Name not recognised");		
		}
	}

	// --------------------------------------------------------------------
	private WebElement findElement(String fieldName) {
		WebElement currentElement = null;
		String fieldLocation = WebElementMap.getWebElementName(fieldName);
		//System.out.println("fieldLocation = " + fieldLocation);
		if(fieldLocation!=null) {
			currentElement = driver.findElement(By.xpath(fieldLocation));
			highlightWebElement(currentElement, "blue");
			TestUtilities.sleepTime(1000);
		} else {
			fieldLocation = fieldName;
			currentElement = driver.findElement(By.xpath(fieldLocation));
			highlightWebElement(currentElement, "blue");
			TestUtilities.sleepTime(1000);
			//// TODO
			//// By.xpath("//*[@id=(" + "//label[text()[(normalize-space(.)='" + fieldName + "')]]" + "/@for)]"));
		}	
		
		return currentElement;
	}

	// -------------------------------------------------------------
	private void highlightWebElement(WebElement myTest, String highlightColour) {

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
					+ "px; " + "height:" + h + "px; " + "top:" + t + "px; " + "left:" + l + "px';"
					+ "document.body.appendChild(btn);");
			TestUtilities.sleepTime(GlobalVariables.highlightTime);
			js.executeScript(
					"var element = document.getElementById('Hello');" + "element.parentNode.removeChild(element);");

		}

	}

}
