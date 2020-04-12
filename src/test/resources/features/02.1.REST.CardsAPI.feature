@REST @SmokeTest
Feature: QA3 - Advanced Automation. REST

#  Task 1 – Simple validation
#  Lets open https://deckofcardsapi.com/
#  Create first test where we shuffle cards and validate that card count is 52
#  Validate - are shuffled. Validate – deck id not null
#
#  Task 2 – Add/Remove data
#  Lets get deck id from the first test and a card from a deck
#  Validate the card parameters
#  Validate that deck amount is 51 now
  @SmokeTest
  Scenario: REST01. Creates new creates another deck and validates deck are unique
    When I shuffle a deck
    And I validate that deck has all parameters
    And I shuffle another deck
    Then I validate that deck is unique