@angular @examples
Feature: Test the interactive examples of angular material

  Scenario Outline: Test the "Filter autocomplete" example of the Autocomplete section
    Given Website opened and navigated to Autocomplete section
    When I enter a "<searchWord>" related to a possible option
    Then the autocomplete options will show "<options>"

    Examples:
      | searchWord | options    |
      | o          | One, Two   |
      | t          | Two, Three |
      | on         | One        |

