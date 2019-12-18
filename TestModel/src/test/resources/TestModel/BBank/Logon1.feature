@BB
@BB01
Feature: BB Basic
#This is a comment
@BB01-01
@SMOKE
#Scenario 1
Scenario: Successful Logon Basic
   When I am on the home page
   Then I set "Username" to "holmesm"
   Then I set "Password" to "Password"
   Then I click "Logon"
   Then I see "Current Account 24.76" on the page
   
@BB01-01a
#Scenario 1a
Scenario: Successful Logon One Step
   When I successfully logon
  
@BB01-03
@Reg
#Scenario 3
Scenario: Regression Test
   When I am on the home page
   Then I set "Username" to "holmesm"
   Then I check "Username" is "holmesm"
   Then I set "Password" to "Password"
   Then I find the "ProductEntry" where "ProductTitle" is "Mortgages"
   Then I click the entry
   Then I click "Logon"
   Then I check "Title" is "Brentford Building Society"
   Then I set "When" to "06-12-2019"
   Then I set "Speed" to "Don't Mind"
   Then I set "//input[@name='amount']" to "123"
   Then I sleep 5000 ms
   
@BB01-04
#Scenario 4
Scenario: Test Set Method
   When I am on the home page
   Then I set "Username" to "holmesm"
   Then I find the "ProductEntry" where "ProductTitle" is "Mortgages"
   Then I click the entry
   Then I click "Logon"
   Then I set "Speed" to "Quick"
   
   
@BB01-99
@Test
#Scenario 99
Scenario: Test Java Method
   When I am on the home page
   Then I sleep 100 ms
   Then I test java method
   