Feature:Dummy Rest API QA Scenarios

Description: This page will contains all rest service .Thease are Fake Online REST API for Testing and Prototyping of sample application which are using rest call to display listing and crud features.



  @Rest1 @RestAPIRegression
  Scenario: Verify the list of user GET request return more than 5 users
    Given User triggers "GET" Request to get list of users
    Then Validates the status code of the response as 200
    Then Validates the content type of the response as "application/json"
    Then Validates the list of users returned in the response are greater than 5


  @Rest2 @RestAPIRegression
  Scenario: Verify that user is able to fire Request to GET single user with ID
    Given User triggers "GET" Request to Single user with ID 4
    Then Validates the status code of the response as 200
    Then Validates the content type of the response as "application/json"
    Then Validates that valid user ID 4 is returned in the response


  @Rest3 @RestAPIRegression
  Scenario Outline: Verify that POST requests for Create a new user
    Given User triggers "POST" Request with <"name">  and <"job">
    Then Validates the status code of the response as 201
    Then Validates the content type of the response as "application/json"
    Then Validates that valid <"name">  and <"job"> is returned in the response

    Examples:
      |name | job|
      |morpheus     | leader   |

  @Rest4 @RestAPIRegression
  Scenario Outline: Verify that POST requests for Register successfully
    Given User triggers "POST" Request with <"email">  and <"password"> for Registering user
    Then Validates the status code of the response as 400
    Then Validates the content type of the response as "application/json"
    Then Validates that error message "Note: Only defined users succeed registration"

    Examples:
      |email | password|
      |eve.dummy@reqres.in     | pistol   |

  @Rest5 @RestAPIRegression
  Scenario Outline: Verify that PUT requests for Update existing user
    Given User triggers "PUT" Request for <"id"> with <"name">  and <"job">
    Then Validates the status code of the response as 200
    Then Validates the content type of the response as "application/json"
    Then Validates that valid <"name">  and <"job"> is returned in the response

    Examples:
      |name | job|id|
      |morpheus     | leader   |2|

  @Rest5 @RestAPIRegression
  Scenario: Verify that DELETE existing user
    Given User triggers "DELETE" Request for user with "2"
    Then Validates the status code of the response as 204
