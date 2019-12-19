@Test
Feature: TestModel - Test Scenarios
#This is a comment

@Test-01

#Scenario 1
Scenario: TagName=Input Type=Text 
   When I go to the "file:///C:/Martin_Holmes_Files/Test%20HTML/Website/Test-SetField.html" Website
   Then I set "Username" to "using label"
   Then I set "//input[@id='xPath']" to "direct xPath"
   Then I set "Parameter" to "Parameter"
   Then I set "TestData" to "+TestData"
   Then I set "RunData" to "#RunData"
   Then I set "Speed" to "Slow"
   Then I set "Send Confirmation Email" to "Checked"
   Then I set "Cars" to "Volvo"
   Then I sleep 5000 ms
   
