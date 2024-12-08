package com.demo.RaghavTuturials.Hooks;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


/**
 * @author Burak Arıkboğa
 * @version 1.0
 * @since 2023-08-02
 *
 * Base class for API testing using RestAssured.

This class definition sets up a base configuration for API testing using RestAssured.

Here's a brief explanation of the class methods:

* `baseURI`: A static final variable that stores the base URL for API requests.
* `response`: A static variable that stores the response from API requests.
* `requestSpecification`: A variable that defines a request specification with the base URI and headers set to accept and send JSON data.

Note that there are no methods in this class definition, only variables. The `given()` method is a static method from RestAssured that is used to create a request specification.
 */
public class Base {
    
    protected static final String baseURI = "https://reqres.in/api";
    protected static Response response;

    protected RequestSpecification requestSpecification = given()
            .baseUri(baseURI)
            .header("Content-Type", "application/json")
            .header("Accept", "application/json");
}
