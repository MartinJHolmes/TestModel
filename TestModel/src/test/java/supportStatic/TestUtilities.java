// © Martin Holmes 2019
package supportStatic;

import gherkin.ast.Scenario;

public class TestUtilities {
	
	public static void sleepTime(int timeMilliseconds) {
        try {
        	if(!GlobalVariables.noSleep) {
				Thread.sleep(timeMilliseconds);
        	}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void sleepTime() {
        try {
        	if(!GlobalVariables.noSleep) {
				Thread.sleep(GlobalVariables.sleepBetweenSteps);
        	}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	//####################################################################
	public static void printDebugMessage(String debugMessage) {
		if (GlobalVariables.debugOn) {
			String currentMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
			
			System.out.println("Debug:" + currentMethod + "() " + debugMessage);
		}
	}
	
	//####################################################################
	public static void printErrorMessage(String errorMessage) {
			String currentMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
			System.out.println(">>>>ERROR>>>>:" + currentMethod + "() " + errorMessage);
	}

}
