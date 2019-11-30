package TestModel.SDF;


import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import Support.GlobalVariables;
import Support.World;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class BeforeAfter {
	
	private World world;
	//private Scenario scenario;
	
	public BeforeAfter(World world) {
		this.world = world;
		System.out.println("Constructor called");
	}
	
	// Would like to included these into a single method that can be called from any where
	WebDriver driver = null;
	//private Scenario scenario;
	//public TestWebElement myTestWebElement = new TestWebElement(driver);
	//RunData myRunData = new RunData();
	//private Scenario scenario;


	@Before
	public void before(Scenario scenario) {
		System.out.println(">>>>>>>> STARTED <<<<<<<<<<<<");
		
        
        System.out.println("Thread ID " + Thread.currentThread().getId() + " " + scenario.getName());
		
	    //this.scenario = scenario;
	    Integer lines = scenario.getLine();
	    //Integer myNumber = lines(0);
	    //System.out.println("List is " + lines.get(0));
	    System.out.println("The scenario = " + scenario.getName() + " " + lines);
	    System.setProperty(GlobalVariables.webDriverType,GlobalVariables.webDriverFileLocation);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		//WebTest.driver = driver;
		System.out.println(world.myTestWebElement.myName);
		world.myTestWebElement.driver = driver;
	    
	}
	
	@After
	public void after(Scenario scenario) {
		System.out.println(scenario.getStatus());
		
		world.myTestWebElement.driver.close();
		System.out.println(">>>>>>>> FINISHED <<<<<<<<<<<<");
	}
	


};
