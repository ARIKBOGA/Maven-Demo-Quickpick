package com.demo.RaghavTuturials.Hooks;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Base {
    
    protected static final String baseURI = "https://reqres.in/api";
    protected static Response response;

    RequestSpecification requestSpecification = given()
            .baseUri(baseURI)
            .header("Content-Type", "application/json")
            .header("Accept", "application/json");
}
