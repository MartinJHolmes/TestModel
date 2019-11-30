package TestModel.SDF.Bank4;

import Support.World;
import TestModel.Environ.GlobalVariables;
import TestModel.Support.LoadData;
import TestModel.Support.TestWebElement;
import io.cucumber.java.en.When;


public class Bank4_2 {
	
	private World world;
	
	public Bank4_2(World world) {
		this.world = world;
	}
	
	@When("I enter the First Name as {string} and Last Name as {string}")
	public void i_enter_the_First_Name_as_and_Last_Name_as(String string, String string2) {
		TestWebElement.setValueTo("First Name", string);
		TestWebElement.setValueTo("Last Name", string2);
		System.out.println(">>> myString = " + world.myString);
		System.out.println(">>>>> I enter the First Name ... was called");
	}

};
