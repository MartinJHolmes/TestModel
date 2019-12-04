@BB
Feature: BB Basic
#This is a comment
#Scenario 3
Scenario: Successful Logon
   When I am on the home page
   Then I set "Username" to "holmesm"
   Then I set "Password" to "Password"
   Then page contains "Withdraw"
   Then I click "Logon"
   Then I check "Title" is "Brentford Building Society"
   
