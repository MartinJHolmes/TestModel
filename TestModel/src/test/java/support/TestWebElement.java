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

public class TestWebElement {

	public WebDriver driver;
	public String myName = "Martin";

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public TestWebElement() {

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkValue(WebElement we, String fieldName, String fieldValue) {
		WebElement returnWE = we.findElement(By.xpath(".//span[@class=(\"a-color-base\")]"));
		System.out.println("checkValue: METHOD NOT COMPLETE");
		TestUtilities.sleepTime();
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkValue(String fieldName, String fieldValue) {
		WebElement returnWE = driver.findElement(By.xpath("//h1"));

		assertEquals(fieldName + " did not match expected", returnWE.getText(), fieldValue);
		TestUtilities.sleepTime();
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void checkPage(String fieldValue) {
		WebElement element = driver.findElement(By.xpath("//*[contains(.,.)]"));
		assertTrue(fieldValue + " was not found", element.getText().contains(fieldValue));
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
		myWE.click();
		TestUtilities.sleepTime();
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
				if (child.getText().equals(Value)) {
					highlightWebElement(child, "blue");
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

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void setRadioButtonTo(String fieldName, String fieldValue) {
		WebElement child = null;
		String xPathString = "";

		xPathString = "//input[@type='radio' and @value='" + fieldValue + "' and @name='" + fieldName + "']";

		child = driver.findElement(By.xpath(xPathString));
		child.click();

		try {
			Thread.sleep(GlobalVariables.sleepBetweenSteps);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		highlightWebElement(child, "blue");
		TestUtilities.sleepTime();
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
			Thread.sleep(GlobalVariables.sleepBetweenSteps);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Check if Label exists for fieldName

		try {
			child = driver.findElement(
					By.xpath("//*[@id=(" + "//label[text()[(normalize-space(.)='" + fieldName + "')]]" + "/@for)]"));

			child.clear();
			child.sendKeys(fieldValue);
		} catch (Exception e) {

			child = driver.findElement(By.xpath(WebElementMap.getWebElementIdentifyBy(fieldName)));
			child.sendKeys(fieldValue);

		}

		highlightWebElement(child, "blue");
		TestUtilities.sleepTime();
	}

	// ####################################################################
	protected void highlightWebElement(WebElement myTest, String highlightColour) {

		if (GlobalVariables.highlightWebElementRequired == true) {
			System.out.println(myTest.getAttribute("id"));
			int w = myTest.getSize().getWidth() + 8;
			int h = myTest.getSize().getHeight() + 8;
			int t = myTest.getLocation().getY() - 6;
			int l = myTest.getLocation().getX() - 8;
			System.out.println(myTest.getAttribute("id") + "Width = " + w);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(" " + "var btn=document.createElement('DIV');" + "btn.id = 'Hello';"
					+ "btn.style = 'border:3px solid #dd0000; position: absolute;" + "width: " + w + "px; " + "height:"
					+ h + "px; " + "top:" + t + "px; " + "left:" + l + "px';" + "document.body.appendChild(btn);");
			TestUtilities.sleepTime(GlobalVariables.highlightTime);
			js.executeScript(
					"var element = document.getElementById('Hello');" + "element.parentNode.removeChild(element);");

			
		}
		

	}

}
