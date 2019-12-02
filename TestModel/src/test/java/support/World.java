package support;

public class World {
	
	// This class is available to all Classes similar to a Static but is thread safe
	// The scope and life is per scenario
	
	public String myString = "World Class";
	public TestWebElement myTestWebElement = new TestWebElement();
    public RunData myRunData = new RunData();
    public LoadData myLoadData = new LoadData();
    
    public String testWorldScope = "initial"; // used to proving scope of the World Class
    
    public World(){
    	System.out.println("World Created");
    }

}
