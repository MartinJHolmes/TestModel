package supportStatic;

public class ThreadData {
	
	static String[][] MapData = new String[5][2];
	
	public static String Value = "2";
	
	public static void printValue() {
		String tempString = "";
		for (int i=0;i<MapData.length;i++) {
			tempString = tempString + "'" + MapData[i][0] + "','" + MapData[i][1] + "'";
		}
		System.out.println("ThreadData = " + tempString);
		return;
	}
	
	public static void setValuePair(String field, String fieldValue) {
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
	
	public static String getValuePair(String field) {
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
