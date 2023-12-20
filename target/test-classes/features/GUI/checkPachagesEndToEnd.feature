Feature: Check packages Type,price and currency for each country [Bahrain - Ksa - Kuwait]


  Scenario: Validate on packages types , prices and currency for Bahrain country
    Given open app portal on the home page
    Then validate landing on the home page successfully
    And click on Change Country above the page
    Then validate landing on the change country popup screen displayed successfully
    Then validate all available countries in the list
    And change the selected country to Bahrain
    Then validate current country changes to Bahrain on homePage successfully
    Then validate package names displayed correctly and with the proper order
    Then validate prices displayed correctly relative to selected country Bahrain
    Then validate the currency displayed properly and relative to the selected country 'Bahrain'


  Scenario: Validate on packages types , prices and currency for Ksa country
    Given open app portal on the home page
    Then validate landing on the home page successfully
    And click on Change Country above the page
    Then validate landing on the change country popup screen displayed successfully
    Then validate all available countries in the list
    And change the selected country to Ksa
    Then validate current country changes to Ksa on homePage successfully
    Then validate package names displayed correctly and with the proper order
    Then validate prices displayed correctly relative to selected country Ksa
    Then validate the currency displayed properly and relative to the selected country Ksa


  Scenario: Validate on packages types , prices and currency for Kuwait country
    Given open app portal on the home page
    Then validate landing on the home page successfully
    And click on Change Country above the page
    Then validate landing on the change country popup screen displayed successfully
    Then validate all available countries in the list
    And change the selected country to Kuwait
    Then validate current country changes to Kuwait on homePage successfully
    Then validate package names displayed correctly and with the proper order
    Then validate prices displayed correctly relative to selected country Kuwait
    Then validate the currency displayed properly and relative to the selected country Kuwait




