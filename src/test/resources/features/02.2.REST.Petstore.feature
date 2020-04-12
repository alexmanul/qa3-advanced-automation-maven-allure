@REST
Feature: QA3 - Advanced Automation. REST. Swagger Petstore

#  Task 2 – SWAGGER UI – part 1 - Pet Store
#  1. Let's open https://swagger.io/tools/swagger-ui/ and press LIVE Demo button
#  2. Get PET with id 1 -> Validate visually that you receive a Cat with name Begemot
#  3. Edit PET with id 3 -> change status to Dead
#  4. Get pet with id 3
#  5. Delete pet with id 3
#  6. Get pet with id 3
#
#  Task 2 – SWAGGER UI – part 2 – Users and orders
#  1. Update user 2 with your name
#  2. Get the user 2
#  3. Delete the user 2
#  4. Get the user 2
#  5. Login and logut with user with id 1
#  6. Create order with id 10
#  7. Get order with id 10
#  8. Delete order with id 10
#  9. Get order with id 10
#  10. Have 10 minutes free to play around

  @SmokeTest
  Scenario Outline: SWG01. User can get pet information
	When I get pet information by '<ID>' id
	And I verify response status code is '200'
	Then I verify pet information by '<ID>' id contains values
	  | JSON_KEY | JSON_VALUE |
	  | id       | <ID>       |
	  | name     | <NAME>     |

	Examples:
	  | ID | NAME   |
	  | 5  | duck   |
	  | 3  | doggie |

  @SmokeTest
  Scenario Outline: SWG02. User can create, update and delete pet information
	When I create pet information with name '<NAME>' and '<ID>' id
	And I delete pet information by '<ID>' id
	And I verify response status code is '200'
	And I get pet information by '<ID>' id
	And I verify response status code is '404'
	And I create pet information with name '<NAME>' and '<ID>' id
	And I update pet information by '<ID>' id with next values
	  | JSON_KEY | JSON_VALUE |
	  | status   | <STATUS>   |
	Then I verify pet information by '<ID>' id contains values
	  | JSON_KEY | JSON_VALUE |
	  | status   | <STATUS>   |

	Examples:
	  | NAME  | ID     | STATUS    |
	  | Manul | 589673 | available |
	  | Kotik | 443556 | pending   |
	  | Simba | 258494 | sold      |