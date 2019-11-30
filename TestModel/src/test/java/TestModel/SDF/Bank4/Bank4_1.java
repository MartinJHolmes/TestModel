package TestModel.SDF.Bank4;

import Support.GlobalVariables;
import Support.World;
import TestModel.Support.LoadData;
import io.cucumber.java.en.When;


public class Bank4_1 {
	
	private World world;
	
	public Bank4_1(World world) {
		this.world = world;
	}
	
//	// Would like to included these into a single method that can be called from any where
//	WebDriver driver = null;
//     public myTestWebElement WebTest = new TestWebElement(driver);
//	RunData myRunData = new RunData();
	//private Scenario scenario;
	
	@When("I am on the Bank{int} page")
	public void i_am_on_the_Bank_page(int bankNumber) {
		System.out.println(">>>>>> I am on the Bank page was called");
		world.myString = "Hello";
		System.out.println("myString = " + world.myString);
		switch(bankNumber) {
		case 4:
			world.myTestWebElement.gotoURL(GlobalVariables.detailsPage);
			break;
		case 5:
			world.myTestWebElement.gotoURL("file:///c://Martin_Holmes_Files//Test HTML//BankDate.html");
			break;
		}
		
	}


	
	@When("I type in {string}, {string}, {string}, {string}, {string}, {string}")
	public void i_enter(String string, String string2, String string3, String string4, String string5, String string6) {
		
		world.myTestWebElement.setValueTo("First Name", string);
		world.myTestWebElement.setValueTo("Middle Name", string2);
		world.myTestWebElement.setValueTo("Last Name", string3);
		world.myTestWebElement.setValueTo("House Number", string4);
		world.myTestWebElement.setValueTo("Line 1", string5);
		world.myTestWebElement.setValueTo("Line 2", string6);

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
		

		world.myTestWebElement.setValueTo("First Name",myLoadData.getItem("First Name"));
		world.myTestWebElement.setValueTo("Last Name",myLoadData.getItem("Last Name"));
		world.myTestWebElement.setValueTo("Line 1",myLoadData.getItem("Line 1"));
		
//		Thread.sleep(2000);
	}
	
	@When("I enter in one step")
	public void i_enter_in_one_step() throws InterruptedException {
		i_am_on_the_Bank_page(4);
		//i_enter_the_First_Name_as_and_Last_Name_as("Father","Christmas");
		// Cannot call this method because it is in another java class
		Thread.sleep(2000);
	}

};
