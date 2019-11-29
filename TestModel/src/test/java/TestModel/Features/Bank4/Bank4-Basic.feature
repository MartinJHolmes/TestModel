@Bank4
@Bank4-Basic
Feature: Bank4 Basic
#This is a comment


@Bank4-01
#Scenario 3
Scenario: Enter minumum personal data
   When I am on the Bank4 page
   And I enter the First Name as "Martin" and Last Name as "Holmes"
 

@Bank4-02
#Scenario 2
Scenario Outline: Enter all fields
	When I am on the Bank4 page
    When I type in "<first>", "<middle>", "<surname>", "<number>", "<line1>", "<line2>"
   
   
Examples:
| first | middle | surname | number | line1 | line2 |
| Martin | James | Holmes | 24 | Greenwood Close | Brentford |
| Conrad | James | Crates | 24 | Greenwood Close | Brentford |


@Bank4-03
#Scenario 2
Scenario: Populate User type data
	When I am on the Bank4 page
    When enter the details for a "Advanced" User
   