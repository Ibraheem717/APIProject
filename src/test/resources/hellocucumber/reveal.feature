Feature: Answering a question
  I put in an answer

  Scenario Outline: User answers a question
    Given the question isn't answered already
    When I give an "<answer>"
    Then I should get "<result>"

  Examples:
    | answer       | result |
    | reveal answer          | True    |
