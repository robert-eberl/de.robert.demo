@webDe @login
Feature: Does the login work?

 Scenario Outline: Successful login
   Given "<website>" has been loaded
   When I enter "<username>" and "<password>"
   Then I am logged in as "<username>"

   Examples:
     | website                            | username     | password     |
     | https://web.de/consent-management/ | itsv.example | itsv.example |


  Scenario Outline: Successful login
    Given "<website>" has been loaded
    When I enter "<username>" and "<password>"
    Then I should see an error message

    Examples:
      | website                            | username     | password      |
      | https://web.de/consent-management/ | itsv.example | wrongPassword |
