@DB
Feature: QA3 - Advanced Automation. Database with background
#  Task 4 – Level up
#  Add cucumber libraries
#  Create a feature with test - Before and after steps using cucumber – same as in Task 3
#  Create a feature with test – Use background steps instead of Before and After steps to setup and drop data
#
#  Task 5 – Launch from File
#  Create a test where you run a query from a file
#  Use MyBatis library
#
#  Task 6 – SQL helper - masterpiece
#  Create a model for SQL – getters/setters or Lombok @Data – username, password, driverName, urlPath
#  – setup all this data in one place
#  Create an SQLHelper class where you can use this data to connect to db
#  SQLHelper – must contain methods – DBConnection, simpleQueryRun(String query), updateQueryRun(String query),
#  simpleParameterValidation(int index, String validateValue), countSimpleValidation(int countAmount)

  Background:
	Given I create 'AGENTS' table in database

  @DropDBTableAgents
  Scenario: DB01. Add and remove data - change agent data
	When I update 'AGENTS' table entity with 'Mukesh' agent name with new values
	  | TABLE_KEY    | NEW_VALUE    |
	  | WORKING_AREA | Bangladesh   |
	  | PHONE_NO     | 777-11111111 |
	Then I verify 'AGENTS' table entity with 'Mukesh' agent name contains values
	  | TABLE_KEY    | NEW_VALUE    |
	  | AGENT_CODE   | A002         |
	  | AGENT_NAME   | Mukesh       |
	  | WORKING_AREA | Bangladesh   |
	  | COMMISSION   | 0.11         |
	  | PHONE_NO     | 777-11111111 |