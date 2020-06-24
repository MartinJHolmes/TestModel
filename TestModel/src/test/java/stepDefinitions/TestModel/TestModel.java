package stepDefinitions.TestModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.World;
import supportStatic.GlobalVariables;
import supportStatic.TestUtilities;

public class TestModel {

	private World world;

	public TestModel(World world) {
		this.world = world;
	}

	@Then("I test java method")
	public void i_test_java_method() {
		// ONLY USED FOR TESTING JAVA METHODS
		//world.myTestWebElement.checkFieldProperty("Test1", "value", "Test2");
		//world.myTestWebElement.checkFieldProperty("Test2", "disabled", "true");
		TestUtilities.writeToFile("Hello");
	}

	@Then("I performance test {int}")
	public void i_test_performance_test(int loopCounter) {
		// ONLY USED FOR TESTING JAVA METHODS
		for (int i = 0; i < loopCounter; i++) {
			world.myTestWebElement.setValue("Test1", "Test" + i);
		}

	}

}
