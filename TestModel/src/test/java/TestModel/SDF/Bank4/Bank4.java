package TestModel.SDF.Bank4;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import TestModel.Environ.GlobalVariables;
import TestModel.Support.LoadData;
import TestModel.Support.RunData;
import TestModel.Support.TestWebElement;
import io.cucumber.java.en.When;


public class Bank4 {
	
//	// Would like to included these into a single method that can be called from any where
//	WebDriver driver = null;
//	//public TestWebElement WebTest = new TestWebElement(driver);
//	RunData myRunData = new RunData();
	//private Scenario scenario;
	
	@When("I am on the Bank{int} page")
	public void i_am_on_the_Bank_page(int bankNumber) {
		switch(bankNumber) {
		case 4:
			TestWebElement.gotoURL(GlobalVariables.detailsPage);
			break;
		case 5:
			TestWebElement.gotoURL("file:///c://Martin_Holmes_Files//Test HTML//BankDate.html");
			break;
		}
		
	}

	@When("I enter the First Name as {string} and Last Name as {string}")
	public void i_enter_the_First_Name_as_and_Last_Name_as(String string, String string2) {
		TestWebElement.setValueTo("First Name", string);
		TestWebElement.setValueTo("Last Name", string2);
		
	}
	
	@When("I type in {string}, {string}, {string}, {string}, {string}, {string}")
	public void i_enter(String string, String string2, String string3, String string4, String string5, String string6) {
		
			TestWebElement.setValueTo("First Name", string);
			TestWebElement.setValueTo("Middle Name", string2);
			TestWebElement.setValueTo("Last Name", string3);
			TestWebElement.setValueTo("House Number", string4);
			TestWebElement.setValueTo("Line 1", string5);
			TestWebElement.setValueTo("Line 2", string6);

	}
	
	@When("enter the details for a {string} User")
	public void enter_the_details_for_a_Standard_User(String string1) throws InterruptedException {
//		Thread.sleep(2000);
		System.out.println(">>>>>>>>>>> " + string1);
		LoadData myLoadData = null;
		switch(string1) {
		case "Standard":
			myLoadData = new LoadData("StandardUser.txt");
			break;
		case "Advanced":
			myLoadData = new LoadData("AdvancedUser.txt");
			break;
		default:
			System.out.println("User type not recognised");
		}
		

		TestWebElement.setValueTo("First Name",myLoadData.getItem("First Name"));
		TestWebElement.setValueTo("Last Name",myLoadData.getItem("Last Name"));
		TestWebElement.setValueTo("Line 1",myLoadData.getItem("Line 1"));
		
//		Thread.sleep(2000);
	}
	
	@When("I enter in one step")
	public void i_enter_in_one_step() throws InterruptedException {
		i_am_on_the_Bank_page(4);
		i_enter_the_First_Name_as_and_Last_Name_as("Father","Christmas");
		Thread.sleep(2000);
	}

};
