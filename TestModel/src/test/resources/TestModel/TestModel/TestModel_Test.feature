@Test
Feature: TestModel - Test Scenarios
#This is a comment

@Test-01
#Scenario 1
Scenario: Set Field Value Tests - FAIL
   When I go to the "file:///C:/Martin_Holmes_Files/Test%20HTML/Website/Test-SetField.html" Website
   Then I sleep 2000 ms
   Then I set "Test1" to "Test1"
   Then I set "Test2" to "Test2"
   Then I set "//input[@id='test3']" to "Test3"
   Then I set "Test4" to "Option 2"
    # Next step will fail
   Then I set "Test4A" to "Option 4"
   Then I set "Test5" to "Checked"
   Then I set "Test5A" to "Not Checked"
   Then I set "Test5B" to "Checked"
   Then I set "Test5C" to "Not Checked"
   Then I set "Test6" to "Option 2"
   Then I set "Test7" to "25-12-2019"
   Then I set "Test8" to "Test8"
   # Next step will fail
   Then I set "//hello" to "Test9"
   # Next step will fail
   Then I set "//label[@id='Test10']" to "Test10"
   Then I check for errors
   Then I sleep 3000 ms
   
@Test-02
#Scenario 2
Scenario: Check Field Value Tests - FAIL 
   When I go to the "file:///C:/Martin_Holmes_Files/Test%20HTML/Website/Test-CheckField.html" Website
   Then I sleep 2000 ms
   Then I check "Test1" is "Test1"
    # Next step will fail
   Then I check "Test1A" is "Test1"
   Then I check "Test2" is "Test2"
   Then I check "//input[@id='test3']" is "Test3"
   Then I check "Test4" is "Option 2"
   Then I check "Test4A" is "Option 2"
   Then I check "Test5" is "Checked"
   Then I check "Test5A" is "Checked"
   Then I check "Test6" is "Option 2"
   Then I check "Test6A" is "Option 2"
   Then I check "Test7" is "16-03-1968"
   Then I check "Test7A" is "16-03-1968" 
   Then I check "Test8" is "Test8"
   Then I check "Test8A" is "Test8"
   Then I check "//Hello" is "Test9"
   Then I check "Test10" is "Test10"
   Then I check "Test11" is "Test11"
   Then I check "//li[1]" is "Test12"
   Then I check "//li[2]" is "Test12a"
   Then I check for errors
   Then I sleep 3000 ms
   

   