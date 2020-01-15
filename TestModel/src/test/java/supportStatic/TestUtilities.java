// © Martin Holmes 2019
package supportStatic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import gherkin.ast.Scenario;

public class TestUtilities {
	
	//####################################################################
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
	
	//####################################################################
	public static void sleepTime() {
        try {
        	if(!GlobalVariables.noSleep) {
				Thread.sleep(GlobalVariables.sleepBetweenSteps);
        	}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	//####################################################################
	public static void waitTime(int timeMilliseconds) {
        try {
				Thread.sleep(timeMilliseconds);
			} catch (InterruptedException e) {
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
	
	//####################################################################
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
    
	//####################################################################
    public static void writeToFile(String writeLine) {
    	String fileName = "c:/TestFolder/report.txt";
    	
//    	File f = new File(fileName);
//    	if(f.exists() && !f.isDirectory()) { 
//    	    System.out.println("File Already exists");
//    	}
    	
    	FileWriter write = null;
		try {
			write = new FileWriter(fileName,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	PrintWriter printLine = new PrintWriter(write);
    	printLine.printf("%s" + "%n", writeLine);
    	printLine.close();
    	
    }

}
