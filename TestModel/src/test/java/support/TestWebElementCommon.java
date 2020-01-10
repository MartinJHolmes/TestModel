// © Martin Holmes 2019
package support;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import supportStatic.GlobalVariables;
import supportStatic.TestUtilities;
import supportStatic.WebElementMap;

public class TestWebElementCommon {

	public WebDriver driver;
	private WebElement currentEntry;
	public String scenario;

	public String myName = "Martin";
	private String actualValue;
	public Boolean failureDetected = false;

	public LoadData myLoadData = new LoadData();
	public RunData myRunData = new RunData();

	private WebElement nullElement = null;

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public TestWebElementCommon() {
		// myWorld = world;
	}

	// --------------------------------------------------------------------
	// private static Boolean calledOutsideOfTestWebElement() {
	// StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
	// if (stElements[3].getClassName().contains("TestWebElement")) {
	// return false;
	// }
	// return true;
	// }

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void failTest() {
		if (failureDetected) {
			System.out.println(">>>>>>>>>>> SCENARIO FAILED - CHECK LOG <<<<<<<<<<<<<<<<<<");
			//driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			assert !failureDetected;
			//String Temp = driver.findElement(By.xpath("/failureDetected")).getText();
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}

	// --------------------------------------------------------------------
	private String transformOutputDateValue(String value) {
		String[] date = value.split("-");
		String newDate = date[2] + "-" + date[1] + "-" + date[0];
		// System.out.println(newDate);
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
			// System.out.println(newValue);
			// System.out.println(newValue.substring(0, 2));
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
				value = newValue.substring(2) + "°";

			default:

			}

		}

		TestUtilities.printDebugMessage("FINISHED");
		return value;
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkFieldProperty(String fieldName, String fieldProperty, String fieldPropertyValue) {
		TestUtilities.printDebugMessage("STARTED");
		// String fieldXPath = WebElementMap.getIdentifyByValue(fieldName);
		List<WebElement> elements = findElements(fieldName);
		highlightWebElement(elements.get(0), "blue");
		String propertyValue = elements.get(0).getAttribute(fieldProperty);
		if (propertyValue == null) {
			// switch(fieldProperty) {
			// case "disabled":
			propertyValue = "false";
			// }
		}
		// System.out.println("Property field: " + fieldName + "; property: " +
		// fieldProperty + ": value=" + propertyValue);
		if (propertyValue.contentEquals(fieldPropertyValue)) {
			highlightWebElement(elements.get(0), "green");
		} else {
			TestUtilities
					.printErrorMessage("Expected Value = " + propertyValue + "   Actual Value = " + fieldPropertyValue);
			highlightWebElement(elements.get(0), "fail");
			failureDetected = true;
		}
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkItemValue(String fieldName, String fieldValue) {
		fieldValue = transformInputValue(fieldValue);
		TestUtilities.printDebugMessage("STARTED");
		String fieldXPath = WebElementMap.getIdentifyByValue(fieldName);
		WebElement returnWE = currentEntry.findElement(By.xpath("." + fieldXPath));
		highlightWebElement(returnWE, "blue");
		// System.out.println("VALUE = " + returnWE.getText());
		if (returnWE.getText().contentEquals(fieldValue)) {
			highlightWebElement(returnWE, "green");
		} else {
			TestUtilities
					.printErrorMessage("Expected Value = " + fieldValue + "   Actual Value = " + returnWE.getText());
			highlightWebElement(returnWE, "fail");
			failureDetected = true;
		}

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
		// System.out.println(elements.size());
		if (elements.size() == 0) {
			TestUtilities.printErrorMessage("No Elements found for '" + fieldName + "'");
			highlightWebElement(nullElement, "fail");
			failureDetected = true;
			return null;

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
				TestUtilities.printErrorMessage("TYPE NAME '" + typeName + "' NOT RECOGNISED >>>");
				highlightWebElement(elements.get(0), "blue");
				highlightWebElement(elements.get(0), "fail");
				failureDetected = true;
				return "";
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
			TestUtilities.printErrorMessage("TAG NAME '" + tagName + "' NOT RECOGNISED >>>");
			highlightWebElement(elements.get(0), "blue");
			highlightWebElement(elements.get(0), "fail");
			failureDetected = true;
		}
		TestUtilities.printDebugMessage("FINISHED");
		return actualValue;

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkFieldValue(String fieldName, String fieldValue) {
		actualValue = "";
		fieldValue = transformInputValue(fieldValue);

		TestUtilities.printDebugMessage("STARTED");
		TestUtilities.printDebugMessage(" fieldValue = " + fieldValue);

		List<WebElement> elements = findElements(fieldName);
		if (elements.size() == 0) {
			TestUtilities.printErrorMessage("No Elements found for '" + fieldName + "'");
			highlightWebElement(nullElement, "fail");
			failureDetected = true;
			return;

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
							TestUtilities.printErrorMessage(
									fieldName + " Expected: " + fieldValue + "  Actual: " + entryLabel.getText());
							failureDetected = true;
							highlightWebElement(entryLabel, "fail");
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
					TestUtilities.printErrorMessage(fieldName + " Expected: " + fieldValue + "  Actual: "
							+ elements.get(0).getAttribute("value"));
					failureDetected = true;
					highlightWebElement(elements.get(0), "fail");
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
					TestUtilities
							.printErrorMessage(fieldName + " Expected: " + fieldValue + "  Actual: " + actualValue);
					failureDetected = true;
					highlightWebElement(elements.get(0), "fail");
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
					TestUtilities
							.printErrorMessage(fieldName + " Expected: " + fieldValue + "  Actual: " + actualValue);
					failureDetected = true;
					highlightWebElement(elements.get(0), "fail");
				}
				// highlightWebElement(elements.get(0), "yellow");
				break;

			default:
				TestUtilities.printErrorMessage("TYPE NAME '" + typeName + "' NOT RECOGNISED >>>");
				highlightWebElement(elements.get(0), "blue");
				highlightWebElement(elements.get(0), "fail");
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
						TestUtilities.printErrorMessage(
								fieldName + " Expected: " + fieldValue + "  Actual: " + option.getText());
						failureDetected = true;
						highlightWebElement(elements.get(0), "fail");
					}
					// System.out.println(tagName + " = '" + option.getText()+ "'");
					// highlightWebElement(elements.get(0), "green");
				}
			}

			break;
		case "li":
		case "h1":
		case "span":
			highlightWebElement(elements.get(0), "blue");
			// System.out.println(tagName + " = " + elements.get(0).getAttribute("value"));
			actualValue = elements.get(0).getText();
			if (actualValue.contentEquals(fieldValue)) {
				highlightWebElement(elements.get(0), "green");
			} else {
				TestUtilities.printErrorMessage(fieldName + " Expected: " + fieldValue + "  Actual: " + actualValue);
				failureDetected = true;
				highlightWebElement(elements.get(0), "fail");
			}
			break;
		default:
			TestUtilities.printErrorMessage("TAG NAME '" + tagName + "' NOT RECOGNISED ");
			highlightWebElement(elements.get(0), "blue");
			highlightWebElement(elements.get(0), "fail");
			failureDetected = true;
		}
		TestUtilities.printDebugMessage("FINISHED");

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkPage(String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		String xPathString = "//*[contains(.,'" + fieldValue + "')]";

		List<WebElement> elements = driver.findElements(By.xpath(xPathString));
		if (elements.size() == 0) {
			TestUtilities.printErrorMessage(fieldValue + "' Not Found on the page");
			highlightWebElement(nullElement, "fail");
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

		String fieldXPath = WebElementMap.getIdentifyByValue(fieldName);
		if (fieldXPath == null) {
			fieldXPath = fieldName;
		}

		WebElement returnWE = driver.findElement(By.xpath(fieldXPath));
		highlightWebElement(returnWE, "green");
		returnWE.click();
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void clickMenu(String topMenu, String bottomMenu) {
		TestUtilities.printDebugMessage("STARTED");
		// WebElement myWE = driver.findElement(By.id(fieldName));

		String fieldXPathTop = WebElementMap.getIdentifyByValue(topMenu);
		if (fieldXPathTop == null) {
			fieldXPathTop = topMenu;
		}

		WebElement eTopMenu = driver.findElement(By.xpath(fieldXPathTop));
		highlightWebElement(eTopMenu, "blue");
		highlightWebElement(eTopMenu, "green");
		eTopMenu.click();

		WebElement eContainer = eTopMenu.findElement(By.xpath("../div"));
		// System.out.println("eContainer tag Name = " + eContainer.getTagName());
		highlightWebElement(eContainer, "blue");

		List<WebElement> elements = eContainer.findElements(By.xpath("./a"));
		if (elements.size() == 0) {
			TestUtilities.printErrorMessage("Sub Menu does not exist for '" + topMenu + "'");
			failureDetected = true;
		} else {
			Iterator<WebElement> iter = elements.iterator();
			// Integer i = 0;
			while (iter.hasNext()) {
				WebElement element = iter.next();
				highlightWebElement(element, "blue");
				if (element.getText().contentEquals(bottomMenu)) {
					highlightWebElement(element, "green");
					element.click();
					return;
				} else {
					highlightWebElement(element, "red");
				}
				// i = i + 1;
			}
		}
		failureDetected = true;
		highlightWebElement(elements.get(0), "fail");
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void findEntry(String entryName, String fieldName, String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		String entryLocation = WebElementMap.getIdentifyByValue(entryName);
		String fieldLocation = WebElementMap.getIdentifyByValue(fieldName);

		if (entryLocation == null) {
			entryLocation = entryName;
		}
		if (fieldLocation == null) {
			fieldLocation = fieldName;
		}

		driver.manage().timeouts().implicitlyWait(GlobalVariables.implicitWait, TimeUnit.SECONDS);
		List<WebElement> entries = driver.findElements(By.xpath(entryLocation));
		Iterator<WebElement> iter = entries.iterator();
		while (iter.hasNext()) {
			WebElement entry = iter.next();

			// highlight each entry
			highlightWebElement(entry, "blue");

			try {
				// find the field
				WebElement element = entry.findElement(By.xpath("." + fieldLocation));
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
					TestUtilities.printErrorMessage("TAG NAME " + tagName + " NOT RECOGNISED ");
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
				TestUtilities.printErrorMessage("The Item '" + fieldLocation + "' does not exist");
			}
			
			
		}
		TestUtilities.printErrorMessage("'" + fieldValue + "' Entry Value not found");
		failureDetected = true;
		highlightWebElement(entries.get(0), "fail");
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void clickEntry() {
		TestUtilities.printDebugMessage("STARTED");

		try { //// ADDED
			currentEntry.click();
		} catch (Exception e) {
			TestUtilities.printDebugMessage("WARNING: element could no be clicked. Alternative will be sought");

			try {
				currentEntry.findElement(By.xpath("..//a")).click();
			} catch (Exception e1) {
				TestUtilities
						.printDebugMessage("WARNING: element could no be clicked. Alternative will be sought (again)");
				WebElement item2 = currentEntry.findElement(By.xpath(".//span"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", item2);
			}

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
			TestUtilities.printErrorMessage("ENTRY WAS NOT FOUND BEFORE CALLING THIS METHOD");
			highlightWebElement(nullElement, "fail");
			failureDetected = true;
		} else {
			setValue(currentEntry, fieldName, fieldValue);
		}
		TestUtilities.sleepTime();
		TestUtilities.printDebugMessage("FINISHED");
	}

	// ---------------------------------------------------------------------
	private void setValue(WebElement currentEntry, String fieldName, String fieldValue) {
		TestUtilities.printDebugMessage("STARTED");
		TestUtilities.printDebugMessage(" fieldValue = " + fieldValue);
		List<WebElement> elements = null;
		if (currentEntry == null) {
			elements = findElements(fieldName);
		} else {
			elements = findEntryElements(currentEntry, fieldName);
		}
		// System.out.println("size " + elements.size());
		if (elements.size() == 0) {
			TestUtilities.printErrorMessage("No Elements found for '" + fieldName + "'");
			highlightWebElement(nullElement, "fail");
			failureDetected = true;
			return;

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
				highlightWebElement(elements.get(0), "fail");
				TestUtilities.printErrorMessage("radio option: " + fieldValue + " does not exist");
				failureDetected = true;
				break;
			case "text":
			case "password":
			case "date":
			case "email":
				highlightWebElement(elements.get(0), "blue");
				if (GlobalVariables.applicationType.contentEquals("REACT")) {
					while (!elements.get(0).getAttribute("value").contentEquals("")) {
						elements.get(0).sendKeys(Keys.BACK_SPACE);
					}
				} else {
					elements.get(0).clear();
				}

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
				break;
			default:
				TestUtilities.printErrorMessage("TYPE NAME '" + typeName + "' NOT RECOGNISED >>>");
				highlightWebElement(elements.get(0), "fail");
				failureDetected = true;
			}
			break;
		case "select":
			highlightWebElement(elements.get(0), "blue");
			elements.get(0).sendKeys(fieldValue);
			highlightWebElement(elements.get(0), "green");
			break;
		default:
			highlightWebElement(elements.get(0), "blue");
			TestUtilities.printErrorMessage("TAG NAME '" + tagName + "' NOT RECOGNISED >>>");
			highlightWebElement(elements.get(0), "fail");
			failureDetected = true;
		}
		TestUtilities.printDebugMessage("FINISHED");
	}

	// --------------------------------------------------------------------
	private List<WebElement> findElements(String fieldName) {
		return findElements(null, fieldName);

	}

	// --------------------------------------------------------------------
	private List<WebElement> findEntryElements(WebElement currentEntry, String fieldName) {
		return findElements(currentEntry, fieldName);

	}

	// --------------------------------------------------------------------
	private List<WebElement> findElements(WebElement currentEntry, String fieldName) {
		// private List<WebElement> findElements(String fieldName) {
		TestUtilities.printDebugMessage("STARTED");
		String fieldLocation;
		List<WebElement> entries = new ArrayList<>();

		String firstChar = fieldName.charAt(0) + "";
		if ((firstChar.equals("/")) | (firstChar.equals(".")) | firstChar.equals("(")) {
			fieldLocation = fieldName;
		} else {
			fieldLocation = WebElementMap.getIdentifyByValue(fieldName);
			if (fieldLocation == null) {
				fieldLocation = "//*[@id=(" + "//label[text()[(normalize-space(.)='" + fieldName + "')]]" + "/@for)]";
			}
		}
		if (currentEntry == null) {
			entries = driver.findElements(By.xpath(fieldLocation));
		} else {
			entries = currentEntry.findElements(By.xpath("." + fieldLocation));
		}

		TestUtilities.printDebugMessage("FINISHED");
		return entries;

	}

	// -------------------------------------------------------------
	private void highlightWebElement(WebElement myTest, String highlightColour) {
		TestUtilities.printDebugMessage("STARTED");

		int w = 0;
		int h = 0;
		int t = 50;
		int l = 300;
		if (myTest != null) {
			w = myTest.getSize().getWidth();
			h = myTest.getSize().getHeight();
			t = myTest.getLocation().getY();
			l = myTest.getLocation().getX();
		}

		if (highlightColour.contentEquals("fail")) {

			// l = l + (w/2) - 30;
			// t = t + (h/2) - 30;
			
			t = t + 2;

			int b1 = 10;
			int r1 = 24;

			int l1 = l + (w / 2) - (r1 / 2) - (b1);
			int t1 = t + (h / 2) - (r1 / 2) - (b1);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("var btn=document.createElement('DIV');" + "btn.id = 'Circle';"
					+ "btn.style.position = 'absolute';"
					+ "btn.style.backgroundColor= 'red';"
					+ "btn.style.border='" + b1 + "px solid red';"
					+ "btn.style.width='" + r1 + "px';" 
					+ "btn.style.height='" + r1 + "px';"
					+ "btn.style.left='" + l1 + "px';" 
					+ "btn.style.top='" + t1 + "px';"
					+ "btn.style.borderRadius='100px';" 
					+ "btn.style.zIndex='1000';"
					+ "document.body.appendChild(btn);");


			int b2 = 4;
			int r2 = 60;

			int l2 = l + (w / 2) - (r2 / 2) - (b2);
			int t2 = t + (h / 2) - (r2 / 2) - (b2);
			
			 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			 js.executeScript("var element = document.getElementById('Circle');"
			 + "element.style.transition = 'all 0.3s';"
			 + "element.style.position = 'absolute';"
			 + "element.style.backgroundColor = 'transparent';"
				+ "element.style.border='" + b2 + "px solid red';"
				+ "element.style.width='" + r2 + "px';" 
				+ "element.style.height='" + r2 + "px';"
				+ "element.style.left='" + l2 + "px';" 
				+ "element.style.top='" + t2 + "px';"
				+ "element.style.borderRadius='100px';" 
			 );
			 try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 Date date = Calendar.getInstance().getTime();  
			 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hhmmss");  
			 String strDate = dateFormat.format(date);  
			 
			 try {
				TestUtilities.takeSnapShot(driver, "c://TestFolder//" + strDate + "-screenshot.jpg") ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			 
			js.executeScript(
					"var element = document.getElementById('Circle');" + "element.parentNode.removeChild(element);");

		} else {

			if (GlobalVariables.highlightWebElementRequired == true & !GlobalVariables.noSleep) {

				w = w + 8;
				h = h + 8;
				t = t - 6;
				l = l - 6;
						
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(" " + "var btn=document.createElement('DIV');"
				+ "btn.id = 'Hello';"
						+ "btn.style = 'border:3px solid " + highlightColour + ";" + " position: absolute;" + "width: "
						+ w
						+ "px; " + "height:" + h + "px; " + "top:" + t + "px; " + "left:" + l + "px; z-index: 1000';"
						+ "document.body.appendChild(btn);");
				TestUtilities.sleepTime(GlobalVariables.highlightTime);
				js.executeScript(
						"var element = document.getElementById('Hello');" + "element.parentNode.removeChild(element);");

			}
		}

		TestUtilities.printDebugMessage("FINISHED");
	}

}
