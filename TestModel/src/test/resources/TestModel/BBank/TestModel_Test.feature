@Test
Feature: TestModel - Test Scenarios
#This is a comment

@Test-01
#Scenario 1
Scenario: TagName=Input Type=Text 
   When I go to the "file:///C:/Martin_Holmes_Files/Test%20HTML/Website/Test-SetField.html" Website
   Then I sleep 2000 ms
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
   Then I sleep 3000 ms
   
@Test-02
#Scenario 2
Scenario: TagName=Input Type=Text 
   When I go to the "file:///C:/Martin_Holmes_Files/Test%20HTML/Website/Test-CheckField.html" Website
   Then I sleep 2000 ms
   Then I check "Test1" is "Test1"
   Then I check "Test1A" is "Test1"
   Then I check "Test2" is "Test2"
   Then I check "//input[@id='test3']" is "Test3"
   Then I check "Test4" is "Option 2"
   Then I check "Test4A" is "Option 2"
   Then I check "Test5" is "Checked"
   Then I check "Test5A" is "Checked"
   Then I check "Test6" is "Option 2"
   Then I check "Test6A" is "Option 2"
   Then I check "Test7" is "1968-03-16"
   Then I check "Test7A" is "1968-03-16"
   Then I check "Test8" is "Test8"
   Then I check "Test8A" is "Test8"
   Then I check for errors
   Then I sleep 3000 ms
   