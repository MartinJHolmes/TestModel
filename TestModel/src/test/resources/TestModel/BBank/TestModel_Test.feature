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
   Then I set "Test5" to "Checked"
   Then I set "Test5A" to "Not Checked"
   Then I set "Test6" to "Option 2"
   Then I set "Test7" to "25-12-2019"
   Then I set "Test8" to "Test8"
   # Next step will fail
   Then I set "//hello" to "Test9"
   # Next step will fail
   Then I set "//Label" to "Test10"
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
   Then I check for errors
   Then I sleep 3000 ms
   
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
   
@Test-05
#Scenario 5
Scenario: Data
   When I go to the "file:///C:/Martin_Holmes_Files/Test%20HTML/Website/Test-Data.html" Website
   Then I sleep 2000 ms
   Then I have the test data in "Test-05-TestData.txt"
   Then I set "Test1" to "@Test1"
   Then I remember for later the value of "Test2" as "#Remembered"
   Then I set "Test2A" to "#Remembered"
   Then I set "Test3" to "[o]15"
   Then I remember for later the value of "//Hello" as "#Failed"
   Then I remember for later the value of "Test5" as "#Date"
   Then I set "Test5A" to "#Date"
   Then I remember for later the value of "Test6" as "#Select"
   Then I set "Test6A" to "#Select"
   Then I check for errors
   Then I sleep 5000 ms
   
@Test-06
#Scenario 6
Scenario: Entries and Items
   When I go to the "file:///C:/Martin_Holmes_Files/Test%20HTML/Website/Test-Entries.html" Website
   Then I sleep 2000 ms
    # Next step will fail
   Then I set "BookPrice" to "7.50" for the entry
   Then I find the "BookEntry" where "BookTitle" is "Child44"
   Then I check "BookPrice" is "12.99" for entry
   Then I find the "BookEntry" where "BookTitle" is "Hitch-hikers guide to the Galaxy"
   Then I check "BookPrice" is "7.50" for entry
    # Next step will fail
   Then I check "BookPrice" is "10.00" for entry
   Then I set ".//input[@class='book_quantity']" to "3" for the entry
   Then I click the entry
   Then I see "Douglas Adams" on the page
    # Next step will fail
   Then I see "Martin Healey" on the page
   Then I click "//div[@class='product']//a"
   Then I find the "//div[@class='product']" where ".//Label[@class='book_title']" is "Hitch-hikers guide to the Galaxy"
   Then I click the entry
    # Next step will fail
   Then I find the "BookEntry" where "BookTitle" is "The Bible"
   Then I check for errors
   Then I sleep 5000 ms

   