package stepDefinitions;
// © Martin Holmes 2019

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import support.World;
import supportStatic.GlobalVariables;
import supportStatic.WebElementMap;

public class BeforeAfter {

	private World world;

	// private Integer myThreadCount = 0;
	// private Long myThreadId = (long) 0;
	private Long realThreadId;
	WebDriver driver = null;

	public BeforeAfter(World world) {
		this.world = world;
	}

	

	@Before
	public void before(Scenario scenario) throws InterruptedException {
		System.out.println(">>>>>>>> SCENARIO STARTED <<<<<<<<<<<<");

		WebElementMap.WebElementMap_Initialise();
		// TEST field map contents
		// WebElementMap.printFieldMap();

		// TEST World scope
		// System.out.println("The current value of testWorldScope = " +
		// world.testWorldScope);
		// world.testWorldScope = scenario.getName();
		// System.out.println("The set value of testWorldScope = " +
		// world.testWorldScope);

		// Each thread sleep for different amounts of time to ensure they don't all
		// access the static ThreadData class at the same time.
		realThreadId = Thread.currentThread().getId();
		Thread.sleep(realThreadId * 100);

		// System.out.println("The actural thread = " + Long.toString(realThreadId));

		// See if thread has already been captured
		String logicalThreadId = supportStatic.ThreadData.getValuePair(Long.toString(realThreadId));
		if (logicalThreadId.equals("No item found")) {
			// If not then capture it
			GlobalVariables.threadCount = GlobalVariables.threadCount + 1;
			supportStatic.ThreadData.setValuePair(Long.toString(realThreadId),
					Integer.toString(GlobalVariables.threadCount));
			logicalThreadId = Long.toString(GlobalVariables.threadCount);
		}

		// System.out.println("Thread ID " + Thread.currentThread().getId() + " " +
		// scenario.getName() );

		Integer lines = scenario.getLine();
		System.out.println("The scenario = " + scenario.getName() + " " + lines);

		// Setup the Web Driver
		System.setProperty(GlobalVariables.webDriverType, GlobalVariables.webDriverFileLocation);
		ChromeOptions options = new ChromeOptions();
		Integer windowWidth = 700;
		options.addArguments("--window-position=0,900");
		options.addArguments("--window-Size=480,100");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// System.out.println(">>>>>>>>> " + logicalThreadId);
		Integer xPos = (Integer.parseInt(logicalThreadId) * windowWidth) - windowWidth;
		// System.out.println("The x position = " + Integer.toString(xPos));
		driver.manage().window().setPosition(new Point(xPos, 0));
		driver.manage().window().setSize(new Dimension(windowWidth, 1000));

		// Thread.sleep(1000);

		world.myTestWebElement.driver = driver;
		world.initialisePages();

	}

	@After
	public void after(Scenario scenario) throws InterruptedException {
		

		world.myTestWebElement.driver.close();
		System.out.println(">>>>>>>> SCENARIO FINISHED <<<<<<<<<<<<");
	}

};
