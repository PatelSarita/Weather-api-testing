Feature: Weather API Testing with OpenWeatherMap

  Background:
    Given The base API URL is loaded from configuration

  # ✅ Positive Scenarios
  Scenario: Get current weather for a valid city
    When I send a GET request to "/weather" with city "London"
    Then The response status code should be 200
    And The response should contain city name "London"

  Scenario: Get weather using latitude and longitude
    When I send a GET request to "/weather" with longitude "139" and latitude "35"
    Then The response status code should be 200
    And The response should contain coordinates "139" and "35"

  Scenario: Get weather in metric units
    When I send a GET request to "/weather" with city "Berlin" and units "metric"
    Then The response status code should be 200
    And The temperature unit should be in "Celsius"

  Scenario: Get weather in Spanish language
    When I send a GET request to "/weather" with city "Madrid" and language "es"
    Then The response status code should be 200
    And The weather description should be in Spanish

  # ❌ Negative Scenarios
  Scenario: Request without API key
    When I send a GET request to "/weather" with city "Paris" and no API key
    Then The response status code should be 401
    And The error message should be "Invalid API key"

  Scenario: Request with invalid city
    When I send a GET request to "/weather" with city "123xyz"
    Then The response status code should be 404
    And The error message should be "city not found"

  Scenario: Request with empty city
    When I send a GET request to "/weather" with an empty city name
    Then The response status code should be 400

  Scenario: Validate additional weather fields
    When I send a GET request to "/weather" with city "London"
    Then The response status code should be 200
    And The response should contain "pressure"
    And The response should contain "humidity"
    And The response should contain "wind"

