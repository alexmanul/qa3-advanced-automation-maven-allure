@UI @SmokeTest
Feature: QA3 - Advanced Automation. Tests for JavaGuru website

  @UI
  Scenario: UI11. Make sample screenshot and store as sample screenshot
    When I navigate to JAVAGURU.LV website
    And I wait for '3' seconds
    Then I take sample screenshot of 'JAVAGURU_MAIN_PAGE' page and store as expected result

  @UI
  Scenario: UI12. Make actual screenshot and check vs sample screenshot
    When I navigate to JAVAGURU.LV website
    And I wait for '3' seconds
    Then I take actual screenshot of 'JAVAGURU_MAIN_PAGE' page and check vs expected result