// � Martin Holmes 2019
package support;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
	private String actualValue;
	public Boolean failureDetected = false;

	public LoadData myLoadData = new LoadData();
	public RunData myRunData = new RunData();

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public TestWebElementCommon() {
		// myWorld = world;
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
	public void failTest() {
		if (failureDetected) {
			System.out.println(">>>>>>>>>>> FAILURE DETECTED - CHECK LOG <<<<<<<<<<<<<<<<<<");
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			String Temp = driver.findElement(By.xpath("/failureDetected")).getText();
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}

	// --------------------------------------------------------------------
	private String transformOutputDateValue(String value) {
		String[] date = value.split("-");
		String newDate = date[2] + "-" + date[1] + "-" + date[0];
		System.out.println(newDate);
		return newDate;
	}

	// --------------------------------------------------------------------
	private String transformInputValue(String value) {
		TestUtilities.printDebugMessage("STARTED");

		String firstChar = value.charAt(0) + "";
		switch (firstChar) {
		case "#":
			// get RunData
			TestUtilities.printDebugMessage("RunData programming NOT complete");
			value = myRunData.getValuePair(value);
			break;
		case "@":
			// get LoadData
			TestUtilities.printDebugMessage("LoadData programming NOT complete");
			value = myLoadData.getItem(value.substring(1));
			break;
		case "[":
			String newValue = value.substring(1);
			System.out.println(newValue);
			System.out.println(newValue.substring(0, 2));
			switch (newValue.substring(0, 2)) {

			case "D]":

				Calendar today = Calendar.getInstance();
				java.util.Date date = today.getTime();
				SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
				String date1 = format1.format(date);

				switch (newValue) {

				case "D]":

					value = date1;
					break;
				default:
					newValue = newValue.substring(2);
					Integer additionalDays = Integer.parseInt(newValue.substring(1, newValue.length() - 1));
					today.add(Calendar.DATE, additionalDays);
					date = today.getTime();
					date1 = format1.format(date);
					value = date1;

				}
				break;

			case "o]":
				value = newValue.substring(2) + "�";

			default:

			}

		}

		TestUtilities.printDebugMessage("FINISHED");
		return value;
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkFieldProperty(String fieldName, String fieldProperty, String fieldPropertyValue) {
		TestUtilities.printDebugMessage("STARTED");
		// String fieldXPath = WebElementMap.getWebElementIdentifyBy(fieldName);
		List<WebElement> elements = findElements(fieldName);
		highlightWebElement(elements.get(0), "blue");
		String propertyValue = elements.get(0).getAttribute(fieldProperty);
		if (propertyValue == null) {
			// switch(fieldProperty) {
			// case "disabled":
			propertyValue = "false";
			// }
		}
		System.out
				.println("Property field: " + fieldName + "; property: " + fieldProperty + ": value=" + propertyValue);
		if (propertyValue.contentEquals(fieldPropertyValue)) {
			highlightWebElement(elements.get(0), "green");
		} else {
			TestUtilities.printDebugMessage(
					"ERROR - Expected Value = " + propertyValue + "   Actual Value = " + fieldPropertyValue);
			highlightWebElement(elements.get(0), "red");
		}

		TestUtilities.printDebugMessage("<<< METHOD NOT WRITTEN >>>");
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkItemValue(String fieldName, String fieldValue) {
		fieldValue = transformInputValue(fieldValue);
		TestUtilities.printDebugMessage("STARTED");
		String fieldXPath = WebElementMap.getWebElementIdentifyBy(fieldName);
		WebElement returnWE = currentEntry.findElement(By.xpath(fieldXPath));
		highlightWebElement(returnWE, "blue");
		//System.out.println("VALUE = " + returnWE.getText());
		if (returnWE.getText().contentEquals(fieldValue)) {
			highlightWebElement(returnWE, "green");
		} else {
			System.out.println(
					">>>> ERROR - Expected Value = " + fieldValue + "   Actual Value = " + returnWE.getText());
			highlightWebElement(returnWE, "red");
			failureDetected = true;
		}

		TestUtilities.printDebugMessage("<<< METHOD NOT WRITTEN >>>");
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void setRememberFieldPair(String fieldName, String remember) {
		myRunData.setValuePair(getFieldValue(fieldName), remember);
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public String getFieldValue(String fieldName) {
		actualValue = "";

		TestUtilities.printDebugMessage("STARTED");

		List<WebElement> elements = findElements(fieldName);
		System.out.println(elements.size());
		if (elements.size() == 0) {
			TestUtilities.printDebugMessage("ERROR - No Elements found for '" + fieldName + "'");
			if (!GlobalVariables.failOnError) {
				TestUtilities.printDebugMessage(">>>> failOnError set to FALSE");
				return null;
			} else {
				// TODO catch error
				return null;
			}
		}

		String tagName = elements.get(0).getTagName();
		switch (tagName) {
		case "input":
			String typeName = elements.get(0).getAttribute("type");

			switch (typeName) {

			case "text":
			case "password":
				highlightWebElement(elements.get(0), "blue");
				// System.out.println(tagName + " = " + elements.get(0).getAttribute("value"));
				actualValue = elements.get(0).getAttribute("value");
				break;
			case "date":
				highlightWebElement(elements.get(0), "blue");
				// System.out.println(tagName + " = " + elements.get(0).getAttribute("value"));
				actualValue = elements.get(0).getAttribute("value");
				actualValue = transformOutputDateValue(actualValue);

				break;

			default:
				TestUtilities.printDebugMessage("<<< TYPE NAME '" + typeName + "' NOT RECOGNISED >>>");
				failureDetected = true;
			}
			break;
		case "select":
			WebElement select = elements.get(0);
			List<WebElement> options = select.findElements(By.xpath("./option"));
			Iterator<WebElement> iter = options.iterator();
			while (iter.hasNext()) {
				WebElement option = iter.next();
				if (option.isSelected()) {
					actualValue = option.getText();
					highlightWebElement(elements.get(0), "blue");
				}
			}

			break;
		default:
			TestUtilities.printDebugMessage("<<< TAG NAME '" + tagName + "' NOT RECOGNISED >>>");
			failureDetected = true;
		}
		TestUtilities.printDebugMessage("FINISHED");
		return actualValue;

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkFieldValue(String fieldName, String fieldValue) {
		actualValue = "";

		if (!calledOutsideOfTestWebElement()) {
			System.out.println("Ignore error");
		}

		TestUtilities.printDebugMessage("STARTED");
		TestUtilities.printDebugMessage(" fieldValue = " + fieldValue);

		List<WebElement> elements = findElements(fieldName);
		if (elements.size() == 0) {
			TestUtilities.printDebugMessage("ERROR - No Elements found for '" + fieldValue + "'");
			if (!GlobalVariables.failOnError) {
				TestUtilities.printDebugMessage(">>>> failOnError set to FALSE");
				return;
			} else {

			}
		}

		String tagName = elements.get(0).getTagName();
		switch (tagName) {
		case "input":
			String typeName = elements.get(0).getAttribute("type");

			switch (typeName) {
			case "radio":
				Iterator<WebElement> iter = elements.iterator();
				while (iter.hasNext()) {
					WebElement entry = iter.next();
					highlightWebElement(entry, "blue");
					if (entry.isSelected()) {
						WebElement entryLabel = entry.findElement(By.xpath("parent::span"));
						highlightWebElement(entryLabel, "blue");

						if (entryLabel.getText().contentEquals(fieldValue)) {

							highlightWebElement(entryLabel, "green");
						} else {
							System.out.println(">>>> ERROR  " + fieldName + " Expected: " + fieldValue + "  Actual: "
									+ entryLabel.getText());
							failureDetected = true;
							highlightWebElement(entryLabel, "red");
							// failTest();
						}
					}
				}

				break;
			case "text":
			case "password":

				highlightWebElement(elements.get(0), "blue");
				// System.out.println(tagName + " = " + elements.get(0).getAttribute("value"));
				actualValue = elements.get(0).getAttribute("value");
				if (elements.get(0).getAttribute("value").contentEquals(fieldValue)) {
					highlightWebElement(elements.get(0), "green");
				} else {
					System.out.println(">>>> ERROR  " + fieldName + " Expected: " + fieldValue + "  Actual: "
							+ elements.get(0).getAttribute("value"));
					highlightWebElement(elements.get(0), "red");
				}
				break;
			case "date":
				highlightWebElement(elements.get(0), "blue");
				// System.out.println(tagName + " = " + elements.get(0).getAttribute("value"));
				actualValue = elements.get(0).getAttribute("value");
				actualValue = transformOutputDateValue(actualValue);

				if (actualValue.contentEquals(fieldValue)) {
					highlightWebElement(elements.get(0), "green");
				} else {
					System.out.println(
							">>>> ERROR  " + fieldName + " Expected: " + fieldValue + "  Actual: " + actualValue);
					highlightWebElement(elements.get(0), "red");
				}
				break;

			case "checkbox":
				highlightWebElement(elements.get(0), "blue");
				actualValue = "NOT Checked";
				if (elements.get(0).isSelected()) {
					actualValue = "Checked";
				}

				if (fieldValue.contentEquals(actualValue)) {
					highlightWebElement(elements.get(0), "green");
				} else {
					System.out.println(
							">>>> ERROR  " + fieldName + " Expected: " + fieldValue + "  Actual: " + actualValue);
					highlightWebElement(elements.get(0), "red");
				}
				// highlightWebElement(elements.get(0), "yellow");
				break;
			
			default:
				System.out.println("<<< TYPE NAME '" + typeName + "' NOT RECOGNISED >>>");
				failureDetected = true;
			}
			break;
		case "select":
			// highlightWebElement(elements.get(0), "blue");

			WebElement select = elements.get(0);

			List<WebElement> options = select.findElements(By.xpath("./option"));
			Iterator<WebElement> iter = options.iterator();
			while (iter.hasNext()) {

				WebElement option = iter.next();

				if (option.isSelected()) {

					if (option.getText().contentEquals(fieldValue)) {
						highlightWebElement(elements.get(0), "blue");
						highlightWebElement(elements.get(0), "green");
					} else {
						highlightWebElement(elements.get(0), "blue");
						System.out.println(">>>> ERROR  " + fieldName + " Expected: " + fieldValue + "  Actual: "
								+ option.getText());
						highlightWebElement(elements.get(0), "red");
					}
					// System.out.println(tagName + " = '" + option.getText()+ "'");
					// highlightWebElement(elements.get(0), "green");
				}
			}

			// System.out.println(tagName + " = '" + elements.get(0).getText()+ "'");
			// System.out.println(tagName + " = '" + fieldValue + "'");
			// if (elements.get(0).getAttribute("value").contains(fieldValue)) {
			// highlightWebElement(elements.get(0), "green");
			// } else {
			// highlightWebElement(elements.get(0), "red");
			// }
			break;
		case "li":
			highlightWebElement(elements.get(0), "blue");
			// System.out.println(tagName + " = " + elements.get(0).getAttribute("value"));
			actualValue = elements.get(0).getText();
			if (actualValue.contentEquals(fieldValue)) {
				highlightWebElement(elements.get(0), "green");
			} else {
				System.out.println(">>>> ERROR  " + fieldName + " Expected: " + fieldValue + "  Actual: "
						+ actualValue);
				highlightWebElement(elements.get(0), "red");
			}
			break;
		default:
			System.out.println("<<< TAG NAME '" + tagName + "' NOT RECOGNISED >>>");
			failureDetected = true;
		}
		TestUtilities.printDebugMessage("FINISHED");

		// if (calledOutsideOfTestWebElement()) {
		// myFieldName = returnValue(fieldName);
		// myFieldValue = returnValue(fieldValue);
		// }

		// case "h1":
		// assertEquals(myFieldName + " did not match expected", returnWE.getText(),
		// myFieldValue);

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkPage(String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		String xPathString = "//*[contains(.,'" + fieldValue + "')]";

		List<WebElement> elements = driver.findElements(By.xpath(xPathString));
		if (elements.size() == 0) {
			System.out.println(">>>> ERROR  '" + fieldValue + "' Not Found on the page");
			failureDetected = true;
		} else {
			Iterator<WebElement> iter = elements.iterator();
			// Integer i = 0;
			while (iter.hasNext()) {
				WebElement element = iter.next();
				highlightWebElement(element, "green");
				// i = i + 1;
			}
		}
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
		// WebElement myWE = driver.findElement(By.id(fieldName));

		String fieldXPath = WebElementMap.getWebElementIdentifyBy(fieldName);
		if(fieldXPath == null) {
			fieldXPath = fieldName;
		}

		WebElement returnWE = driver.findElement(By.xpath(fieldXPath));
		highlightWebElement(returnWE, "green");
		returnWE.click();
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void findEntry(String entryName, String fieldName, String fieldValue) {
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
				case "label":
					elementValue = element.getText();
					break;
				default:
					TestUtilities.printDebugMessage("<<< TAG NAME NOT RECOGNISED >>>  " + tagName);
					failureDetected = true;
				}

				if (elementValue.equals(fieldValue)) {
					highlightWebElement(element, "green");
					// save entry as current
					currentEntry = entry;
					TestUtilities.sleepTime();
					TestUtilities.printDebugMessage("FINISHED");
					return;
				} else {
					highlightWebElement(element, "red");
				}
			} catch (Exception e) {
				TestUtilities.printDebugMessage("<<< METHOD NOT COMPLETE >>>");
			}
		}
		System.out.println(">>>> ERROR  '" + fieldValue + "' Entry Value not found");
		failureDetected = true;
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void clickEntry() {
		TestUtilities.printDebugMessage("STARTED");

		try { //// ADDED
			currentEntry.click();
		} catch (Exception e) {
			currentEntry.findElement(By.xpath("..//a")).click();
		}
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void setValue(String fieldName, String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		// transform input values if special characters are used
		fieldName = transformInputValue(fieldName);
		fieldValue = transformInputValue(fieldValue);

		setValue(null, fieldName, fieldValue);
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void setValueCurrentEntry(String fieldName, String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		// transform input values if special characters are used
		fieldName = transformInputValue(fieldName);
		fieldValue = transformInputValue(fieldValue);

		if (currentEntry == null) {
			System.out.println("<<< ENTRY WAS NOT FOUND BEFORE CALLING THIS METHOD >>>");
			failureDetected = true;
		}
		
		setValue(currentEntry, fieldName, fieldValue);
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ---------------------------------------------------------------------
	private void setValue(WebElement currentEntry, String fieldName, String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		TestUtilities.printDebugMessage(" fieldValue = " + fieldValue);

		List<WebElement> elements = findElements(fieldName);
		if (elements.size() == 0) {
			TestUtilities.printDebugMessage("ERROR - No Elements found for '" + fieldValue + "'");
			if (!GlobalVariables.failOnError) {
				TestUtilities.printDebugMessage(">>>> failOnError set to FALSE");
				return;
			} else {

			}
		}

		String tagName = elements.get(0).getTagName();
		switch (tagName) {
		case "input":
			String typeName = elements.get(0).getAttribute("type");

			switch (typeName) {
			case "radio":
				Iterator<WebElement> iter = elements.iterator();
				while (iter.hasNext()) {
					WebElement entry = iter.next();
					highlightWebElement(entry, "blue");
					// if (entry.getAttribute("value").equals(fieldValue)) {
					WebElement entryLabel = entry.findElement(By.xpath("parent::span"));
					highlightWebElement(entryLabel, "blue");
					// String myString = myWeb.getText();
					if (entryLabel.getText().equals(fieldValue)) {
						highlightWebElement(entryLabel, "green");
						entry.click();
						highlightWebElement(entry, "green");
						return;
					} else {
						highlightWebElement(entryLabel, "red");
					}
				}

				break;
			case "text":
			case "password":
			case "date":
				highlightWebElement(elements.get(0), "blue");
				elements.get(0).clear();
				// Code below is required for React type applications
//				while( !elements.get(0).getAttribute("value").contentEquals("")) {
//					elements.get(0).sendKeys(Keys.BACK_SPACE);
//				}
				   
				elements.get(0).sendKeys(fieldValue);
				highlightWebElement(elements.get(0), "green");
				break;
			case "checkbox":
				highlightWebElement(elements.get(0), "blue");
				if (fieldValue.contentEquals("Checked")) {
					if (elements.get(0).isSelected()) {
						// null
					} else {
						elements.get(0).click();
					}
				} else {
					if (elements.get(0).isSelected()) {
						elements.get(0).click();
					} else {
						// null
					}
				}
				highlightWebElement(elements.get(0), "green");
			default:
				TestUtilities.printDebugMessage("<<< TYPE NAME '" + typeName + "' NOT RECOGNISED >>>");
				failureDetected = true;
			}
			break;
		case "select":
			highlightWebElement(elements.get(0), "blue");
			elements.get(0).sendKeys(fieldValue);
			highlightWebElement(elements.get(0), "green");
			break;
		default:
			TestUtilities.printDebugMessage("<<< TAG NAME '" + tagName + "' NOT RECOGNISED >>>");
			failureDetected = true;
		}
		TestUtilities.printDebugMessage("FINISHED");
	}

	// --------------------------------------------------------------------
//	private List<WebElement> findElements(String fieldName) {
//		return findElements(null, fieldName);
//		
//	}
	
	// --------------------------------------------------------------------
//	private List<WebElement> findEntryElements(WebElement currentEntry, String fieldName) {
//		return findElements(currentEntry, fieldName);
//		
//	}
	
	
	// --------------------------------------------------------------------
	//private List<WebElement> findElements(WebElement currentEntry, String fieldName) {
	private List<WebElement> findElements(String fieldName) {
		TestUtilities.printDebugMessage("STARTED");
		String fieldLocation;
		List<WebElement> entries = new ArrayList<>();

		String firstChar = fieldName.charAt(0) + "";
		if ((firstChar.equals("/")) | (firstChar.equals(".")) | firstChar.equals("(") ) {
			fieldLocation = fieldName;
		} else {
			fieldLocation = WebElementMap.getWebElementName(fieldName);
			if (fieldLocation == null) {
				fieldLocation = "//*[@id=(" + "//label[text()[(normalize-space(.)='" + fieldName + "')]]" + "/@for)]";
			}
		}

		entries = driver.findElements(By.xpath(fieldLocation));

		TestUtilities.printDebugMessage("FINISHED");
		return entries;
	}

	// -------------------------------------------------------------
	private void highlightWebElement(WebElement myTest, String highlightColour) {
		TestUtilities.printDebugMessage("STARTED");

		if (GlobalVariables.highlightWebElementRequired == true & !GlobalVariables.noSleep) {

			int w = myTest.getSize().getWidth() + 8;
			int h = myTest.getSize().getHeight() + 8;
			int t = myTest.getLocation().getY() - 6;
			int l = myTest.getLocation().getX() - 8;

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(" " + "var btn=document.createElement('DIV');" + "btn.id = 'Hello';"
					+ "btn.style = 'border:3px solid " + highlightColour + ";" + " position: absolute;" + "width: " + w
					// + "px; " + "height:" + h + "px; " + "top:" + t + "px; " + "left:" + l +
					// "px';"
					+ "px; " + "height:" + h + "px; " + "top:" + t + "px; " + "left:" + l + "px; z-index: 1000';"
					+ "document.body.appendChild(btn);");
			TestUtilities.sleepTime(GlobalVariables.highlightTime);
			js.executeScript(
					"var element = document.getElementById('Hello');" + "element.parentNode.removeChild(element);");

		}
		TestUtilities.printDebugMessage("FINISHED");
	}

}
