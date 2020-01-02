@REACT
Feature: React Tests
#This is a comment
@REACT-01
#Scenario 1
Scenario: Demo
   When I go to the "https://foxhound87.github.io/mobx-react-form-demo/demo" Website
   Then I sleep 2000 ms
   Then I set "//input[@name='username']" to "Martin Holmes"
   Then I set "//input[@name='email']" to "martin_j_holmes@hotmail.com"
   Then I sleep 2000 ms
   