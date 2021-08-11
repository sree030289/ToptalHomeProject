Feature:Orange HRM Open source management QA Scenarios

  Background:
    Given User navigates to Orange HRM Application


    @AddEmp  @Regression
  Scenario Outline: 01 - Verify Admin is able to add new Employee with upload clear image
    Given User login into Application with "<UserType>" creds
    And he navigates to PIM section and clicks on Add user button
    And he provides "<First Name>","<Last Name>","<picture path>" details and clicks on Save button
    Then navigate to Employee List and Search for the Employee with "<First Name>","<Last Name>"is added successfully
    Then he logout of the application
Examples:
  |UserType|First Name|Last Name|picture path|
  | Admin| fname | jname | toptalImg.jpeg |


    @SearchEmp  @Regression
    Scenario Outline: 02 - Verify Admin is able to search existing employee
      Given User login into Application with "<UserType>" creds
      When he navigates to Admin section and search for "<UserName>"
      Then he is able to find the "<UserName>" in the result table section
      And  he logout of the application

      Examples:
      |UserType| UserName|
      |Admin   | toptalqa1|


      @SubscribeEmp @Regression
      Scenario Outline: 03 - Verify newly added Employee is able to subscribe to marketplace successfully
        Given User login into Application with "<UserType>" creds
        When he navigates to Dashboard section
        And he clicks on subscribe marketplace link and provides "<UserName>" and "<Email>"
        Then Verify subscribe is not displayed
        And  he logout of the application

        Examples:
          |UserType|UserName|Email|
          |EMP     | dummyname       |  dummy@example.com   |


  @SubscribeEmailAdmin @Regression
  Scenario Outline: 04 - Verify Admin is able to subscribe top 2 Leave notifications successfully
    Given User login into Application with "<UserType>" creds
    When he navigates to Admin section
    And  he hovers on configuration and selects email configuration menu item
    Then he subscribes to first two notification types
    And  he logout of the application

    Examples:
      |UserType|
      |Admin   |


    @SearchUserDirector  @Regression
    Scenario Outline: 05 - Verify Admin is able to search users in directory
      Given User login into Application with "<UserType>" creds
      When he navigates to Directory section
      And  he provides "<JobTitle>" parameter for search item
      Then verify the top result match "<JobTitle>" selected in the search
      And  he logout of the application


      Examples:
        |UserType  |JobTitle|
        |Admin     |Chief Technical Officer|
        |Admin     |Chief Executive Officer|