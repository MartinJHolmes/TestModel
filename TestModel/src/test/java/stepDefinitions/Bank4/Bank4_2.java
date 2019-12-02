package stepDefinitions.Bank4;

import io.cucumber.java.en.When;
import support.World;

public class Bank4_2 {

	private World world;

	public Bank4_2(World world) {
		this.world = world;
	}

	@When("I enter the First Name as {string} and Last Name as {string}")
	public void i_enter_the_First_Name_as_and_Last_Name_as(String string, String string2) throws InterruptedException {
		world.myTestWebElement.setValueTo("First Name", string);
		world.myTestWebElement.setValueTo("Last Name", string2);
		Thread.sleep(3000);

		System.out.println("The previous step was '" + world.myRunData.getValuePair("PreviousStep") + "'");
		world.myRunData.setValuePair("TestScope", "Not available");
	}

	@When("I use a Page Factory\"")
	public void i_use_a_Page_Factory() {
		//WebDriver driver = world.myTestWebElement.driver;
		//DetailsPagePoF DetailsPage = PageFactory.initElements(driver, DetailsPagePoF.class);
		//DetailsPage.firstName.sendKeys("Hello");
		
		world.detailsPage.firstName.sendKeys("MerryChristmas");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

};
