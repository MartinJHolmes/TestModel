package TestModel.SDF;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import Support.World;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class BeforeAfter {
	
	private World world;
	//private Scenario scenario;
	private Integer myThreadCount = 0;
	private Long myThreadId = (long) 0;
	private Long realThreadId;
	
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
		
        realThreadId = Thread.currentThread().getId();
        
        if(realThreadId == myThreadId) {
        	Integer i = 5;
        	i = i + 1;
        } else {
        	myThreadId = Thread.currentThread().getId();
        	TestModel.SDF.GlobalVariables.threadCount = TestModel.SDF.GlobalVariables.threadCount + 1;
        	myThreadCount = TestModel.SDF.GlobalVariables.threadCount;
        }
        
        System.out.println("Thread ID " + Thread.currentThread().getId() + " " + scenario.getName() + " ThreadCount = " + myThreadCount);
		
	    //this.scenario = scenario;
	    Integer lines = scenario.getLine();
	    //Integer myNumber = lines(0);
	    //System.out.println("List is " + lines.get(0));
	    System.out.println("The scenario = " + scenario.getName() + " " + lines);
	    System.setProperty(GlobalVariables.webDriverType,GlobalVariables.webDriverFileLocation);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		driver.manage().window().setPosition(new Point(myThreadCount * 100,myThreadCount * 100));
		driver.manage().window().setSize(new Dimension(400,500));
		//WebTest.driver = driver;
		System.out.println(world.myTestWebElement.myName);
		world.myTestWebElement.driver = driver;
	    
	}
	
	@After
	public void after(Scenario scenario) throws InterruptedException {
		System.out.println(scenario.getStatus());
		//Thread.sleep(3000);
		
		world.myTestWebElement.driver.close();
		System.out.println(">>>>>>>> FINISHED <<<<<<<<<<<<");
	}
	


};
