package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import io.cucumber.java.en.*;
import utilities.ConfigurationReader;

public class WeatherApiSteps {

    private Response response;

    @Given("The base API URL is loaded from configuration")
    public void load_base_url() {
        RestAssured.baseURI = ConfigurationReader.get("baseUrl");
    }

    @When("I send a GET request to {string} with city {string}")
    public void sendGetRequestWithCity(String path, String city) {
        response = RestAssured
                .given()
                .queryParam("q", city)
                .queryParam("appid", ConfigurationReader.get("apiKey"))
                .get(path);
    }

    @Then("The response status code should be {int}")
    public void validateStatusCode(int code) {
        Assert.assertEquals(code, response.getStatusCode());
    }
    @Then("The response should contain city name {string}")
    public void validateCityInResponse(String city) {
        String cityName = response.jsonPath().getString("name");
        Assert.assertTrue(cityName.equalsIgnoreCase(city));
    }

    @When("I send a GET request to {string} with longitude {string} and latitude {string}")
    public void SendAGETRequestToWithLongitudeAndLatitude(String path, String lon, String lat) {
        response = RestAssured
                .given()
                .queryParam("lon", lon)
                .queryParam("lat", lat)
                .queryParam("appid", ConfigurationReader.get("apiKey"))
                .get(path);
    }

    @When("I send a GET request to {string} with city {string} and units {string}")
    public void sendGetWithUnits(String path, String city, String units) {
        response = RestAssured
                .given()
                .queryParam("q", city)
                .queryParam("units", units)
                .queryParam("appid", ConfigurationReader.get("apiKey"))
                .get(path);
    }

    @When("I send a GET request to {string} with city {string} and language {string}")
    public void sendGetWithLanguage(String path, String city, String lang) {
        response = RestAssured
                .given()
                .queryParam("q", city)
                .queryParam("lang", lang)
                .queryParam("appid",ConfigurationReader.get("apiKey"))
                .get(path);
    }

    @When("I send a GET request to {string} with city {string} and no API key")
    public void sendWithoutApiKey(String path, String city) {
        response = RestAssured
                .given()
                .queryParam("q", city)
                .get(path);
    }

    @When("I send a GET request to {string} with an empty city name")
    public void sendWithEmptyCity(String path) {
        response = RestAssured
                .given()
                .queryParam("q", "")
                .queryParam("appid", ConfigurationReader.get("apiKey"))
                .get(path);
    }

    @Then("The response should contain coordinates {string} and {string}")
    public void validateCoordinates(String expectedLon, String expectedLat) {
        int actualLon = response.jsonPath().getInt("coord.lon");
        int actualLat = response.jsonPath().getInt("coord.lat");
        int expectedLongitude = Integer.parseInt(expectedLon);
        int expectedLatitude = Integer.parseInt(expectedLat);
        Assert.assertEquals(expectedLatitude, actualLat);
        Assert.assertEquals(expectedLongitude, actualLon);

    }

    @Then("The temperature unit should be in {string}")
    public void validateTemperatureUnit(String unit) {
        float temp = response.jsonPath().getFloat("main.temp");
        Assert.assertTrue(temp > -100 && temp < 100);
    }

    @Then("The weather description should be in Spanish")
    public void validateLanguageResponse() {
        String description = response.jsonPath().getString("weather[0].description");
        Assert.assertFalse(description.matches("[A-Za-z]+"));
    }

    @Then("The error message should be {string}")
    public void validateErrorMessage(String message) {
        String msg = response.jsonPath().getString("message");
        Assert.assertTrue(msg.toLowerCase().contains(message.toLowerCase()));
    }

    @And("The response should contain {string}")
    public void theResponseShouldContain(String fieldName) {
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        Assert.assertTrue(fieldName,responseBody.contains(fieldName));
    }
}

