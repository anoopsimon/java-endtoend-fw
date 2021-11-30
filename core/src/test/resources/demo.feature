Feature: Hello World

  @demo
  Scenario Outline: Login to app
    Given I login to app
    And I create an account for test user
    Examples:
    |user|
    |random|
    |random|
    |random|
    |random|
    |random|
    |random|
    |random|
    |random|