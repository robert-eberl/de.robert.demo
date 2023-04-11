@angular @stackblitz
Feature: Test the stackblizt page and function on material.angular.io

  Scenario: Test functionality and the page call of stackblitz.com from material.angular.io
    Given stackblitz link has been clicked on the material.angular.io page
    When template is changed
    And style is changed
    And typescript logic is changed
    Then it will be portrait properly