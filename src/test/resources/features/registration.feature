Feature: Student Name Field Validation

  Background:
    Given the user is on the registration page

  Scenario Outline: Validate Student Name field
    When the user enters "<name>" into the student name field
    And fills all other fields with valid data
    And clicks the register button
    Then the result should be "<result>"

  Examples:
    | name                      | result                                                      |
    |                           | Provide value for student name                              |
    | A                         | Minimum 2 characters required                               |
    | Ab                        | Registration was successful. The roll number generated      |
    | John123                   | No numbers allowed in student name                          |
    | @John!                    | No special characters allowed                               |
    | Johnathan Edward Christopher Maxwell| Maximum 30 characters allowed                     |
    | John Smith                | Registration was successful. The roll number generated      |