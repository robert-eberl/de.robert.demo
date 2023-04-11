@itsv @browserTest
Feature: Website-Test within different browsers

  @firefox
  Scenario Outline: Test the <page>-page in firefox
    Given I open the "<page>"-page
    Then The "<page>"-page is shown correctly

    Examples:
      | page          |
      | Home          |
      | AboutUs       |
      | WeAsEmployers |

  @chrome
  Scenario Outline: Test the <page>-page in chrome
    Given I open the "<page>"-page
    Then The "<page>"-page is shown correctly

    Examples:
      | page          |
      | Home          |
      | AboutUs       |
      | WeAsEmployers |
