@ANG8
Feature: Sample Test of Angular 8 application
#This is a comment
@ANG8-01
#Scenario 1
Scenario: Weather App
   When I go to the "https://minimus-weather.web.app/login" Website
   Then I sleep 5000 ms
   Then I set "Username" to "abc@hotmail.com"
   Then I set "Password" to "abcdef"
   Then I click "Login"
   Then I check for errors
   Then I sleep 3000 ms
   