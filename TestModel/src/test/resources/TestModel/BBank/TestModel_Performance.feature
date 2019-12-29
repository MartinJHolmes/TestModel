@Performance
Feature: TestModel - Performance Tests
#This is a comment

@Performance-01
#Scenario Performance-01
Scenario: Simple Performance Test 
   When I go to the "file:///C:/Martin_Holmes_Files/Test%20HTML/Website/Test-SetField.html" Website
   Then I sleep 2000 ms
   Then I performance test
   Then I sleep 3000 ms
   