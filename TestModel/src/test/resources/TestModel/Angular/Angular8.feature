@ANG8
Feature: Sample Test of Angular 8 application
#This is a comment
@ANG8-02
#Scenario 1
Scenario: Weather App1
   When I go to the "https://minimus-weather.web.app/login" Website
   Then I sleep 3000 ms
   Then I set "Username" to "abc@hotmail.com"
   Then I set "Password" to "abcdef"
   Then I click "Login"
   
   Then I sleep 3000 ms
   Then I find the "CityEntry" where "CityNameItem" is "PARIS"
   Then I click the entry
   
   Then I sleep 1000 ms
   Then I check "CurrentCityTemp" is "[o]12"
   Then I find the "ForecastEntry" where "ForecastDay" is "SAT"
   Then I check "ForecastTemp" is "[o]5" for entry
   Then I check for errors
   Then I sleep 3000 ms
   
   