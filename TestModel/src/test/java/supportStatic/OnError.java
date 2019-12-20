// © Martin Holmes 2019
package supportStatic;

public class OnError {
	public static int errorCode = 0;
	public static String errorMessage = "None";
	public static String logicalFieldName = "None";
	public static String testName = "None";
	public static int testStep = 0;
	public static String fieldName = "None";
	public static String exceptionMessage = "None";
	public static String testWebElementMethod = "None";
	
	public static void printMessage() {
		String errorOutPut;
		errorOutPut = testName + " ";
		errorOutPut = errorOutPut + "STEP-" + testStep + " ";
		
		errorOutPut = errorOutPut + "TEST METHOD (" + testWebElementMethod + ") ";
		errorOutPut = errorOutPut + "LOGICALFIELDNAME (" + logicalFieldName + ") ";
		errorOutPut = errorOutPut + errorMessage;
		
		 
		System.out.println(errorOutPut);
	}

}
