// © Martin Holmes 2019
package support;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import supportStatic.OnError;
import supportStatic.TestUtilities;

public class LoadData {

String[][] testData = new String[5][2];


public LoadData() {
	
}

   public void insertData(String testDataFilename) {
	   
	   TestUtilities.printDebugMessage("STARTED");
       // Open test data file associated with the test
       String fileName= "c://Martin_Holmes_Files//Test HTML//TestData//" + testDataFilename; // + "-TestData.txt";
       try{
       File file= new File(fileName);
       Scanner inputStream;
       
           inputStream = new Scanner(file);
           int i = 0;
           while(inputStream.hasNext()){
               String line= inputStream.nextLine();
               testData[i] = line.split(";");
               i++;
           }

           inputStream.close();
       }catch (FileNotFoundException e) {
    		OnError.errorMessage = "LoadData{} test data file '" + fileName + "' does not exist" ;
			OnError.printMessage();
			TestUtilities.printDebugMessage("FINISHED");
			return;
       }
       //printValue();
       TestUtilities.printDebugMessage("FINISHED");
   }

    
    public String getItem(String findLogicalName){
    	TestUtilities.printDebugMessage("STARTED");	

    	//printValue();
    	
    	String returnString;
    	returnString = "";
    	for (int i=0; i<testData.length; i++ ) {
    		if (findLogicalName.equals(testData[i][0])) {
    			returnString = testData[i][1];
    			TestUtilities.printDebugMessage("FINISHED");
    			return returnString;
    		}
    	}
    	OnError.errorMessage = "LoadData.getItem() '" + findLogicalName + "' does not exist in test data file" ;
		OnError.printMessage();
		TestUtilities.printDebugMessage("FINISHED");
    	return returnString;
    	
    }
    
	public void printValue() {
		TestUtilities.printDebugMessage("STARTED");	
		String tempString = "";
		for (int i=0;i<testData.length;i++) {
			tempString = tempString + "'" + testData[i][0] + "','" + testData[i][1] + "'";
		}
		TestUtilities.printDebugMessage(tempString);
		TestUtilities.printDebugMessage("FINISHED");
		return;
	}

}