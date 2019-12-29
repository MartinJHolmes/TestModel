// © Martin Holmes 2019

// TODO
// 1 Consider including in LoadData or the other way around
package support;

import supportStatic.OnError;
import supportStatic.TestUtilities;

public class RunData {
	
	String[][] MapData = new String[5][2];
	
	public String Value = "2";
	
	public void printValue() {
		TestUtilities.printDebugMessage("STARTED");
		String tempString = "";
		for (int i=0;i<MapData.length;i++) {
			tempString = tempString + "'" + MapData[i][0] + "','" + MapData[i][1] + "'";
		}
		TestUtilities.printDebugMessage(tempString);
		TestUtilities.printDebugMessage("FINISHED");
		return;
	}
	
	public void setValuePair(String fieldValue, String field) {
		// find the next blank row and insert the values
		TestUtilities.printDebugMessage("STARTED");
		for (int i=0;i<MapData.length;i++) {
			String tempValue = MapData[i][0] + "";
			if (tempValue.equals("null")) {
				MapData[i][0] = field;
				MapData[i][1] = fieldValue;
				i = MapData.length;
			}
		}

		printValue();
		TestUtilities.printDebugMessage("FINISHED");
		return;
	}
	
	public String getValuePair(String field) {
		// find the row which matches the field name
		TestUtilities.printDebugMessage("STARTED");
		printValue();
		for (int i=0;i<MapData.length;i++) {
			String tempValue = MapData[i][0] + "";
		
			if (tempValue.equals(field)) {
				return MapData[i][1];
			}
		}
		
		OnError.errorMessage = "RunData.getValuePair() variable'" + field + "' does not exist" ;
		OnError.printMessage();
		TestUtilities.printDebugMessage("FINISHED");
		return "No item found";
	}
	

}
