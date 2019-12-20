@Test
Feature: TestModel - Test Scenarios
#This is a comment

@Test-01

#Scenario 1
Scenario: TagName=Input Type=Text 
   When I go to the "file:///C:/Martin_Holmes_Files/Test%20HTML/Website/Test-SetField.html" Website
   Then I set "Test1" to "Test1"
   Then I set "Test2" to "Test2"
   Then I set "//input[@id='test3']" to "Test3"
   Then I set "Test4" to "Option 2"
   Then I set "Test5" to "Checked"
   Then I set "Test6" to "Option 2"
   Then I set "Test7" to "25-12-2019"
   Then I set "Test8" to "Test8"
   Then I set "Test9" to "#RunData9"
   Then I set "Test10" to "@LoadData10"
   Then I sleep 5000 ms
   
   
