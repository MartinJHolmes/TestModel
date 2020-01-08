@ANG
Feature: Angular Tests
#This is a comment
@ANG-01
#Scenario 1
Scenario: Todo List
   When I go to the "https://angularjs.org/" Website
   Then I sleep 5000 ms
   Then I set "todoList.todoText" to "Test Angular"
   #Then I set "//*[@ng-model='todoList.todoText']" to "Test Angular"
   Then I click "//*[@value='add']"
   Then I check "(//*[@ng-repeat='todo in todoList.todos'])[3]" is "Test Angular"
   Then I click "(//*[@ng-repeat='todo in todoList.todos'])[3]"
   Then I sleep 2000 ms
   