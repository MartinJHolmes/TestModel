// © Martin Holmes 2019
package support;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import supportStatic.OnError;

public class LoadData {

String[][] testData = new String[5][2];


public LoadData() {
	
}

   public void insertData(String testDataFilename) {
	   

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
			return;
       }
       
   }

    
    public String getItem(String findLogicalName){
    	
    	// Debug
    	//for (int j=0; j<testData.length; j++) {
    	//	System.out.println(testData[j][0]);
    	//}
    	
    	
    	String returnString;
    	returnString = "";
    	for (int i=0; i<testData.length; i++ ) {
    		if (findLogicalName.equals(testData[i][0])) {
    			returnString = testData[i][1];
    			return returnString;
    		}
    	}
    	OnError.errorMessage = "LoadData.getItem() '" + findLogicalName + "' does not exist in test data file" ;
		OnError.printMessage();
    	return returnString;
    }

}