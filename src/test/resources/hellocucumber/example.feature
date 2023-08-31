Feature: Next question
  The users wants to get a question

  Scenario Outline: User wants a new question
    Given a question
    When I give a "<category>" and ask for a new question
    Then I should get a different question

  Examples:
    | catagory       |
    | music          |
    |                |
