Feature: Sorting and Filtering Apparel

  As a customer I want to be abel to sorting and filtering apparel by different parameters.

  @Filtering
  Scenario: Filtering Products by Size
    Given I am on the "Apparel" webpage
    When I select the size "M"
    Then I should see products available in size "M"

  @Sorting @ToBeExecute
  Scenario Outline: Sorting Products by <Attribute>
    Given I am on the "Apparel" webpage
    When I choose to sort products by "product.<Attribute>.<Order>"
    Then the products should be displayed in "<Order>" order of "<Attribute>"
    Examples:
      | Attribute | Order |
      | name      | asc   |
      | name      | desc  |
      | price     | asc   |
      | price     | desc  |


  @Filtering
  Scenario: Filtering Products by Color
    Given I am on the "Apparel" webpage
    When I select the color "Blue"
    Then I should see products available in the color "Blue"

  @Sorting
  Scenario: Sorting Products by Rating (High to Low)
    Given I am on the "Apparel" webpage
    When I choose to sort products by rating from high to low
    Then the products should be displayed in descending order of rating

  @Filtering
  Scenario: Filtering Products by Price Range
    Given I am on the "Apparel" webpage
    When I set the price range from 50 to 100
    Then I should see products within the specified price range


