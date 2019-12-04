package supportStatic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class WebElementMap {
	
	private static String[][] MapData = new String[50][5];
	//protected TestClass myTest;
	public static String myString = "Web Map";


	   public static void WebElementMap_Initialise() {
		   
		   
		   //this.myTest = test;
		   myString = "It changed";
		   

//	       lines = Arrays.copyOf(lines, lines.length + 1);
//	       lines[2] = values.split(",");
	      
		   
	       String fileName= GlobalVariables.fieldMapLocation;
	       File file= new File(fileName);
	       
	       
	   	BufferedReader br = null;
		FileReader fr = null;
		try {
			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String sCurrentLine;
			int i = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				MapData[i] = sCurrentLine.split(",");
				MapData[i][1] = MapData[i][1].replaceAll("~", ",");
				i++;
				//System.out.println(sCurrentLine);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		//System.out.println("WebElmentMap created");
		//printFieldMap();

	       
//	       Scanner inputStream;
//	       try{
//	           inputStream = new Scanner(file);
//	           int i = 0;
//	           while(inputStream.hasNext()){
//	               String line= inputStream.next();
//	               System.out.println("line = " + line);
//	               MapData[i] = line.split(",");
//	               //String[] values = line.split(",");
//	               // this adds the currently parsed line to the 2-dimensional string array
//	               //lines.add(Arrays.asList(values));
//	               i++;
//	           }
//
//	           inputStream.close();
//	       }catch (FileNotFoundException e) {
//				OnError.errorMessage = "FieldMap - FieldMap file does not exist" ;
//				OnError.printMessage();
//				return;
//	           //e.printStackTrace();
//	       }
	       
	   }
	
	    public static String getWebElementName(String findLogicalName){
	    	return getWebElement(findLogicalName,"Name");
	    }
	    
	    public static String getWebElementType(String findLogicalName){
	    	return getWebElement(findLogicalName,"Type");
	    }
	    
	    public static String getWebElementIdentifyBy(String findLogicalName){
		    return getWebElement(findLogicalName,"IdentifyBy");
	    }
	    
	    public static String getWebElementFrame(String findLogicalName){
		    return getWebElement(findLogicalName,"Frame");
	    }
	    
	    protected static String getWebElement(String findLogicalName, String WebElementProperty) {
	    	//printFieldMap();
	    	String returnString;
	    	int fieldMapColumn = 0;
	    	
	    	switch (WebElementProperty) {
	    	case "Name":
	    		fieldMapColumn = 1;
	    		break;
	    	case "Type":
	    		fieldMapColumn = 2;
	    		break;
	    	case "IdentifyBy":
	    		fieldMapColumn = 3;
	    		break;
	    	case "Frame":
	    		fieldMapColumn = 4;
	    		break;
	    	default:
	    		OnError.errorMessage = "FieldMap - property '" + WebElementProperty + "' is not a valid option";
				OnError.printMessage();
	    	}
	    	returnString = findLogicalName;
	    	//System.out.println("findLogialName = " + findLogicalName + fieldMapColumn + MapData.length);
	    	

		    	for (int i=0; i<MapData.length; i++ ) {
		    		if (findLogicalName.equals(MapData[i][0])) {
		    			returnString = MapData[i][fieldMapColumn];
		    			//System.out.println("counter = " + i);
		    			return returnString;
		    			
		    		}
		    	}	
	    	OnError.errorMessage = "FieldMap - logicalWebElementName '" + findLogicalName + "' does not exist in the map";
			OnError.printMessage();
            //System.out.println("Field Map = " + returnString);
			int i = 1 /0;
			return null;	
	    }
	    public static void printFieldMap() {
	    	for (int i=0; i<MapData.length; i++ ) {
	    		String printText = MapData[i][0] + "," + MapData[i][1] + "," + MapData[i][2] + "," + MapData[i][3] + "," + MapData[i][4];
	    		System.out.println(printText);
	    			
	    		}
	    }

}
