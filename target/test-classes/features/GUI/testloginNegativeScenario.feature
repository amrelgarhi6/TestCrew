Feature: Check login negative scenario for each country [Bahrain - Ksa - Kuwait]



  Scenario: Validate displaying an error message upon trying to sign in with invalid credentials for Bahrain country
    Given open app portal on the home page
    And click on Change Country above the page
    Then validate landing on the change country popup screen displayed successfully
    And change the selected country to Bahrain
    And click on Sign-In button to navigate into Login page
    Then validate landing on Login page successfully
    And Enter an invalid email
    And click on Next button to validate the email
    Then validate an error message displayed successfully and notify the user with correct format and instructions to proceed



  Scenario: Validate displaying an error message upon trying to sign in with invalid credentials for Ksa country
    Given open app portal on the home page
    And click on Change Country above the page
    Then validate landing on the change country popup screen displayed successfully
    And change the selected country to Ksa
    And click on Sign-In button to navigate into Login page
    And Enter an invalid email
    And click on Next button to validate the email
    Then validate an error message displayed successfully and notify the user with correct format and instructions to proceed



  Scenario: Validate displaying an error message upon trying to sign in with invalid credentials for Kuwait country
    Given open app portal on the home page
    And click on Change Country above the page
    Then validate landing on the change country popup screen displayed successfully
    And change the selected country to Kuwait
    And click on Sign-In button to navigate into Login page
    And Enter an invalid email
    And click on Next button to validate the email
    Then validate an error message displayed successfully and notify the user with correct format and instructions to proceed




