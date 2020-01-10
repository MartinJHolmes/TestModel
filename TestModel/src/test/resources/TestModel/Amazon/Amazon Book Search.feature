Feature: Amazon Book Search
#This is a comment

@Amazon
@Amazon-01
#Scenario 3
Scenario: Search Child44
   When I go to the "https://www.amazon.co.uk" Website
   Then I sleep 10000 ms
   Then I set "SearchBox" to "Child44 book"
   Then I click "SearchGo"
   Then I find the "AmazonBookEntry" where "YearItem" is "1987"

