package support;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import supportStatic.GlobalVariables;
import supportStatic.TestUtilities;
import supportStatic.WebElementMap;

public class TestWebElement {

	public WebDriver driver;
	public String myName = "Martin";

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public TestWebElement() {
		// Get Classes passed to the class
		// ===========================
		// this.driver = driver;
		System.out.println("TestWebElementNS constructor");

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkValue(WebElement we, String fieldName, String fieldValue) {
		WebElement returnWE = we.findElement(By.xpath(".//span[@class=(\"a-color-base\")]"));
		System.out.println(returnWE.getText());

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkValue(String fieldName, String fieldValue) {
		WebElement returnWE = driver.findElement(By.xpath("//h1"));

		assertEquals(fieldName + " did not match expected", returnWE.getText(), fieldValue);

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkPage(String fieldValue) {
		WebElement element = driver.findElement(By.xpath("//*[contains(.,.)]"));
		assertTrue(fieldValue + " was not found",element.getText().contains(fieldValue));
		if (element.getText().contains(fieldValue)) {
			System.out.println(">>>>> FOUND!!!!!!!!");
		} else {
			System.out.println(">>>>> NOT FOUND!!!!!!!!");
		}
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void gotoURL(String URL) {
		driver.get(URL);

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void click(String fieldName) {
		WebElement myWE = driver.findElement(By.id(fieldName));
		myWE.click();

	}

	public WebElement findCollectionItem(String Collection, String Item, String Value) {
		WebElement aWe = null;

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> parent = driver.findElements(By.xpath(Collection));
		Iterator<WebElement> iter = parent.iterator();
		while (iter.hasNext()) {
			WebElement we = iter.next();
			highlightWebElement(we, "red");
			try {
				WebElement child = we.findElement(By.xpath(Item));
				highlightWebElement(child, "yellow");
				System.out.println("The years is " + child.getText());
				if (child.getText().equals(Value)) {
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

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void setValueTo(String fieldName, String fieldValue) {

		WebElement child = null;

		// <<<< TODO
		// Replaces _* with "Special"
		// Needs to lookup in Load or Run Data
		Character c1 = '_';
		Character c2 = fieldValue.charAt(0);

		if (c1.equals(c2)) {
			fieldValue = "Special";
			// TODO get actual vale
		}

		// >>>>
		

		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Check if Label exists for fieldName
		System.out.println("//*[@id=(" + "//label[text()[(normalize-space(.)='" + fieldName + "')]]" + "/@for)]");
		try {
			child = driver.findElement(
					By.xpath("//*[@id=(" + "//label[text()[(normalize-space(.)='" + fieldName + "')]]" + "/@for)]"));

			child.clear();
			child.sendKeys(fieldValue);
		} catch (Exception e) {
			// Check if field with that name exists
//			child = driver.findElement(By.id(fieldName));
//			child.clear();
//			child.sendKeys(fieldValue);
			
			child = driver.findElement(By.xpath(WebElementMap.getWebElementIdentifyBy(fieldName)));
			child.sendKeys(fieldValue);

		}

		highlightWebElement(child, "blue");

	}

	// ####################################################################
	protected void highlightWebElement(WebElement myWebElement, String highlightColour) {

		if (GlobalVariables.highlightWebElementRequired == true) {

			try {
				for (int i = 0; i < 2; i++) {
					if (driver instanceof org.openqa.selenium.JavascriptExecutor) {
						((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
								"arguments[0].style.border='3px solid " + highlightColour + "';", myWebElement);
					}

					TestUtilities.sleepTime(GlobalVariables.highlightTime);
					if (driver instanceof org.openqa.selenium.JavascriptExecutor) {
						((org.openqa.selenium.JavascriptExecutor) driver)
								.executeScript("arguments[0].style.border='1px solid green'", myWebElement);

					}
				}
			} catch (Exception e) {
				// System.out.println("Could not highlight");
			}

			try {

				if (driver instanceof org.openqa.selenium.JavascriptExecutor) {
					((org.openqa.selenium.JavascriptExecutor) driver)
							.executeScript("arguments[0].style.border='1px solid green'", myWebElement);

				}

			} catch (Exception e) {
				System.out.println("Could not highlight");
			}

		}
	}

}
