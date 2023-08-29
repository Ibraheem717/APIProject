Feature: Did I get the question correct?
  I put in an answer

  Scenario Outline: Is question correct?
    Given the question isn't answered already
    When I give an "<answer>"
    Then I should get "<result>"

  Examples:
    | answer       | result |
    | reveal answer          | True    |
