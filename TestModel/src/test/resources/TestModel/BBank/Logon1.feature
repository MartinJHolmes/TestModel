@BB
@BB01
Feature: BB Basic
#This is a comment
@BB01-01
#Scenario 1
Scenario: Successful Logon
   When I am on the home page
   Then I set "Username" to "holmesm"
   Then I set "Password" to "Password"
   Then I click "Logon"
   Then I check "Title" is "Brentford Building Society"
   Then page contains "Withdraw"
   Then I set "When" to "06-12-2019"
   Then I set RadioButton "Speed" to "Quick"

@BB01-02
#Scenario 2
Scenario: Successful Logon
   When I am on the home page
   Then I draw a line