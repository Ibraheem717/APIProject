Feature: Do I get a question?
  The system wants to get a question

  Scenario Outline: Is a questioned given or not?
    Given quiz takes a catagory
    When I give a "<catagory>"
    Then I should get "<question>"

  Examples:
    | catagory       | question |
    | music          | True     |
    |                | True     |
    | null           | False    |