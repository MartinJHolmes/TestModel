package TestModel.Support;

public class RunData {
	
	String[][] MapData = new String[5][2];
	
	public String Value = "2";
	
	public void printValue() {
		String tempString = "";
		for (int i=0;i<MapData.length;i++) {
			tempString = tempString + "'" + MapData[i][0] + "','" + MapData[i][1] + "'";
		}
//		if (GlobalVariables.debugOn) {
			System.out.println("RunData: Printing... " + tempString);
//		}
		return;
	}
	
	public void setValuePair(String field, String fieldValue) {
		// find the next blank row and insert the values
		//System.out.println("setValuePair " + field + " " + fieldValue);
		for (int i=0;i<MapData.length;i++) {
			String tempValue = MapData[i][0] + "";
			//System.out.println(tempValue);
			if (tempValue.equals("null")) {
				MapData[i][0] = field;
				MapData[i][1] = fieldValue;
				i = MapData.length;
			}
		}
		//MapData[0][0] = field;
		//MapData[0][1] = fieldValue;
		printValue();
		return;
	}
	
	public String getValuePair(String field) {
		// find the row which matches the field name
		printValue();
		for (int i=0;i<MapData.length;i++) {
			String tempValue = MapData[i][0] + "";
			//System.out.println(tempValue);
			if (tempValue.equals(field)) {
				return MapData[i][1];
			}
		}
		
//		OnError.errorMessage = "RunData.getValuePair() variable'" + field + "' does not exist" ;
//		OnError.printMessage();
		return "No item found";
	}
	

}
