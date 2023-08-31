Feature: Do I get a question?
  The users wants to get a question

  Scenario Outline: Is a questioned given or not?
    Given a question
    When I give a "<catagory>" and ask for a new question
    Then I should get a different question

  Examples:
    | catagory       |
    | music          |
    |                |
