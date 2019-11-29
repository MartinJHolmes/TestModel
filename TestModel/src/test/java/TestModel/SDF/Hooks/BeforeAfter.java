package TestModel.SDF.Hooks;

import java.util.ArrayList;
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
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BeforeAfter {
	
	// Would like to included these into a single method that can be called from any where
	WebDriver driver = null;
	//public TestWebElement WebTest = new TestWebElement(driver);
	RunData myRunData = new RunData();
	private Scenario scenario;

	@Before
	public void before(Scenario scenario) {
	    this.scenario = scenario;
	    List<Integer> lines = scenario.getLines();
	    //Integer myNumber = lines(0);
	    //System.out.println("List is " + lines.get(0));
	    System.out.println("The scenario = " + scenario.getName() + " " + lines.get(0));
	    System.setProperty(GlobalVariables.webDriverType,GlobalVariables.webDriverFileLocation);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		//WebTest.driver = driver;
		TestWebElement.driver = driver;
	    
	}
	
	@After
	public void after(Scenario scenario) {
		System.out.println(scenario.getStatus());
		System.out.println(">>>>>>>> FINISHED <<<<<<<<<<<<");
		TestWebElement.driver.close();
	}
	


};
