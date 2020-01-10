@Test
Feature: TestModel - Test Scenarios
#This is a comment


   
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
   Then I remember for later the value of "Test7" as "#Failed"
   Then I remember for later the value of "Test8" as "#Failed"
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
   Then I set "//input[@class='book_quantity']" to "3" for the entry
   Then I click the entry
   Then I see "Douglas Adams" on the page
    # Next step will fail
   Then I see "Martin Healey" on the page
   Then I click "//div[@class='product']//a"
   Then I find the "//div[@class='product']" where "//Label[@class='book_title']" is "Hitch-hikers guide to the Galaxy"
   Then I click the entry
    # Next step will fail
   Then I find the "BookEntry" where "BookTitle" is "The Bible"
    # Next step will fail
   Then I find the "BookEntry" where "//AAA" is "Add"
   Then I check for errors
   Then I sleep 5000 ms

   