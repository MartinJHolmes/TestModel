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
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class BeforeAfter {
	
	// Would like to included these into a single method that can be called from any where
	WebDriver driver = null;
	public TestWebElement myTestWebElement = new TestWebElement(driver);
	RunData myRunData = new RunData();
	private Scenario scenario;

	@Before
	public void before(Scenario scenario) {
		System.out.println(">>>>>>>> STARTED <<<<<<<<<<<<");
	    this.scenario = scenario;
	    Integer lines = scenario.getLine();
	    //Integer myNumber = lines(0);
	    //System.out.println("List is " + lines.get(0));
	    System.out.println("The scenario = " + scenario.getName() + " " + lines);
	    System.setProperty(GlobalVariables.webDriverType,GlobalVariables.webDriverFileLocation);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		//WebTest.driver = driver;
		TestWebElement.driver = driver;
	    
	}
	
	@After
	public void after(Scenario scenario) {
		System.out.println(scenario.getStatus());
		
		TestWebElement.driver.close();
		System.out.println(">>>>>>>> FINISHED <<<<<<<<<<<<");
	}
	


};
