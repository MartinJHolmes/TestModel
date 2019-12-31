package stepDefinitions.BrentfordBank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.World;
import supportStatic.TestUtilities;

public class TestModel {

	private World world;

	public TestModel(World world) {
		this.world = world;
	}

	@Then("I test java method")
	public void i_test_java_method() {
		// ONLY USED FOR TESTING JAVA METHODS
		world.myTestWebElement.checkFieldProperty("Test1", "value", "Test2");
		world.myTestWebElement.checkFieldProperty("Test2", "disabled", "true");
	}

	@Then("I performance test")
	public void i_test_performance_test() {
		// ONLY USED FOR TESTING JAVA METHODS
		for (int i = 0; i < 2; i++) {
			world.myTestWebElement.setValue("Test1", "Test" + i);
		}

	}

}
