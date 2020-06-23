@Test
Feature: TestModel - Test Scenarios
#This is a comment

@Test-Access
#Scenario 1
Scenario: Accessibility
   When I go to the "file:///C:/Martin_Holmes_Files/Test%20HTML/Website/Test-Accessibility.html" Website
   Then I sleep 2000 ms
   Then I set "Test1" to "Test1"
   Then I set "Access2" to "Option2"
   Then I check for errors
   Then I sleep 3000 ms