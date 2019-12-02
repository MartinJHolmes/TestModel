// TODO
// 1 Consider including in LoadData or the other way around
package support;

import supportStatic.OnError;

public class RunData {
	
	String[][] MapData = new String[5][2];
	
	public String Value = "2";
	
	public void printValue() {
		String tempString = "";
		for (int i=0;i<MapData.length;i++) {
			tempString = tempString + "'" + MapData[i][0] + "','" + MapData[i][1] + "'";
		}

		return;
	}
	
	public void setValuePair(String field, String fieldValue) {
		// find the next blank row and insert the values

		for (int i=0;i<MapData.length;i++) {
			String tempValue = MapData[i][0] + "";
			//System.out.println(tempValue);
			if (tempValue.equals("null")) {
				MapData[i][0] = field;
				MapData[i][1] = fieldValue;
				i = MapData.length;
			}
		}

		printValue();
		return;
	}
	
	public String getValuePair(String field) {
		// find the row which matches the field name
		printValue();
		for (int i=0;i<MapData.length;i++) {
			String tempValue = MapData[i][0] + "";
		
			if (tempValue.equals(field)) {
				return MapData[i][1];
			}
		}
		
		OnError.errorMessage = "RunData.getValuePair() variable'" + field + "' does not exist" ;
		OnError.printMessage();
		return "No item found";
	}
	

}
