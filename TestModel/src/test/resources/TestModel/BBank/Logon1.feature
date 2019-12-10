@BB
@BB01
Feature: BB Basic
#This is a comment
@BB01-01
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

@BB01-02
#Scenario 2
Scenario: Successful Logon
   When I am on the home page
   Then I try to click
   Then I try something different
   
@BB01-03
#Scenario 3
Scenario: Successful Logon 3
   When I am on the home page
   Then I set "Username" to "holmesm"
   Then I check "Username" is "holmesm"
   Then I set "Password" to "Password"
   Then chose product
   Then I find the entry where "text" is "Mortgages"
   Then I click "Logon"
   Then I check "Title" is "Brentford Building Society"
   Then page contains "Withdraw"
   Then I set "When" to "06-12-2019"
   Then I set RadioButton "Speed" to "Quick"