package stepDefinitions;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import support.World;
import supportStatic.GlobalVariables;


public class BeforeAfter {
	
	private World world;

	//private Integer myThreadCount = 0;
	//private Long myThreadId = (long) 0;
	private Long realThreadId;
	
	public BeforeAfter(World world) {
		this.world = world;
	}
	

	WebDriver driver = null;

	@Before
	public void before(Scenario scenario) throws InterruptedException {
		System.out.println(">>>>>>>> STARTED <<<<<<<<<<<<");
		
		// Testing World scope
		System.out.println("The current value of testWorldScope = " + world.testWorldScope);
		world.testWorldScope = scenario.getName();
		System.out.println("The set value of testWorldScope = " + world.testWorldScope);
		
        realThreadId = Thread.currentThread().getId();
        
        Thread.sleep(realThreadId * 100);
        
        System.out.println("The actural thread = " + Long.toString(realThreadId));
        
        String logicalThreadId = supportStatic.ThreadData.getValuePair(Long.toString(realThreadId));
        
        if(logicalThreadId.equals("No item found")) {
        	GlobalVariables.threadCount = GlobalVariables.threadCount + 1;
        	supportStatic.ThreadData.setValuePair(Long.toString(realThreadId),Integer.toString(GlobalVariables.threadCount) );
        	logicalThreadId = Long.toString(GlobalVariables.threadCount);
        	
        }
        
//        if(realThreadId == myThreadId) {
//        	Integer i = 5;
//        	i = i + 1;
//        } else {
//        	myThreadId = Thread.currentThread().getId();
//        	TestModel.SDF.GlobalVariables.threadCount = TestModel.SDF.GlobalVariables.threadCount + 1;
//        	myThreadCount = TestModel.SDF.GlobalVariables.threadCount;
//        }
        
        //TestModel.SDF.GlobalVariables.threadCount = 0;
        
        System.out.println("Thread ID " + Thread.currentThread().getId() + " " + scenario.getName() );
		
	    Integer lines = scenario.getLine();
	    System.out.println("The scenario = " + scenario.getName() + " " + lines);
	    System.setProperty(GlobalVariables.webDriverType,GlobalVariables.webDriverFileLocation);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		System.out.println(">>>>>>>>> " + logicalThreadId);
		Integer xPos = (Integer.parseInt(logicalThreadId) * 480) - 480;
		System.out.println("The x position = " + Integer.toString(xPos));
		driver.manage().window().setPosition(new Point(xPos,0));
		driver.manage().window().setSize(new Dimension(400,500));
		
		//Thread.sleep(1000);
		
		world.myTestWebElement.driver = driver;
		world.initialisePages();
	    
	}
	
	@After
	public void after(Scenario scenario) throws InterruptedException {
		
		world.myTestWebElement.driver.close();
		System.out.println(">>>>>>>> FINISHED <<<<<<<<<<<<");
	}
	


};
