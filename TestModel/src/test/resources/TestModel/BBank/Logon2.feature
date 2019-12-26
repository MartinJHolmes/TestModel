@BB
@BB02
Feature: BB Extra
#This is a comment
@BB02-01
@Demo
#Scenario 3
Scenario: Successful Logon
   When I am on the home page
   Then I sleep 2000 ms
   Then I set "Username" to "holmesm"
   Then I set "Password" to "Password"
   Then I click "Logon"
   Then I set "When" to "06-12-2019"
   Then I set "Speed" to "Quick"
   Then I set "Username" to "healeym"
   Then I set "Password" to "Password"
   Then I set "Username" to "holmesm"
   Then I set "Password" to "Password"
   Then I sleep 2000 ms
   

@BBC
#Scenario 99
Scenario: BBC Weather Test
   When I go to the "https://www.bbc.co.uk/weather" Website
   Then I sleep 2000 ms
   Then I set "WeatherSearch" to "LE5 6JJ"
   Then I click "WeatherSearchGo"
   Then I find the "DayEntry" where "DateItem" is "Sun 29th" 
   Then I click the entry
   Then I find the "TimeEntry" where "HourItem" is "08"
   Then I check "HourTemp" is "7°" for entry
   Then I click the entry
   Then I find the "TimeEntry" where "HourItem" is "09"
   Then I click the entry
   Then I find the "TimeEntry" where "HourItem" is "10"
   Then I click the entry
   Then I find the "DayEntry" where "DateItem" is "Sat 28th"
   Then I click the entry
   Then I sleep 2000 ms