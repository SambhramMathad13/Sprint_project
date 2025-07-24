Feature: Student Name Field Validation

  Background:
    Given the user is on the registration page

  Scenario Outline: Validate Student Name field
    When the user enters "<name>" into the student name field
    And fills all other fields with valid data
    And clicks the register button
    Then an appropriate error message should be displayed or registration proceeds accordingly

    Examples:
      | name             |
      |                  |  # Empty input
      | A                |  # Too short
      | Ab               |  # Minimum length
      | JohnDoe123       |  # Contains numbers
      | @John!           |  # Contains special characters
      | Johnathan Edward Christopher Maxwell |  # Exceeds max length
      | John Smith       |  # Valid input