package TestModel.SDF.Bank4;


import Support.World;

import io.cucumber.java.en.When;


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

};
