// © Martin Holmes 2019
package supportStatic;

import java.io.File;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

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
	
    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File(fileWithPath);

                //Copy file at destination

                //FileUtils.copyFile(SrcFile, DestFile);
                FileHandler.copy(SrcFile, DestFile);

    }

}
