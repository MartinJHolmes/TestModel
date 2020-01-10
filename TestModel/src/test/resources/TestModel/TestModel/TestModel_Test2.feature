@Test
Feature: TestModel - Test Scenarios
#This is a comment


   
@Test-03
#Scenario 3
Scenario: Check Field Property - FAIL 
   When I go to the "file:///C:/Martin_Holmes_Files/Test%20HTML/Website/Test-CheckProperty.html" Website
   Then I sleep 2000 ms
   Then I check the "Test1" has the property "disabled" set to "true"
   Then I check the "Test1A" has the property "disabled" set to "false"
   Then I check the "Test2" has the property "disabled" set to "true"
   Then I check the "Test2A" has the property "disabled" set to "false"
   Then I check the "Test3" has the property "test_hook" set to "Test3"
   Then I check the "Test3A" has the property "test_hook" set to "Test3"
   Then I check for errors
   Then I sleep 3000 ms
   
@Test-04
#Scenario 4
Scenario: Transform Date Value 
   When I go to the "file:///C:/Martin_Holmes_Files/Test%20HTML/Website/Test-TransformDateValue.html" Website
   Then I sleep 2000 ms
   Then I set "Test1" to "25-12-2019"
   Then I check "Test2" is "25-12-2019"
   Then I check "Test2A" is "25-12-2019"
   Then I set "Test3" to "[D]"
   Then I set "Test4" to "[D]+5d"
   Then I set "Test5" to "[D]+20d"
   Then I check for errors
   Then I sleep 5000 ms
   


   