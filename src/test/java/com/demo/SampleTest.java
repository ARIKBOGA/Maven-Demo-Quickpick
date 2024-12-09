package com.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class SampleTest {

    // Base URL for the API
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Test
    @DisplayName("Get a Post by ID")
    @Description("This test verifies that we can retrieve a post by its ID.")
    public void testGetPostById() {
        int postId = 1;

        // Perform the GET request and validate the response
        given()
                .baseUri(BASE_URL)
                .pathParam("id", postId)
                .when()
                .get("/posts/{id}")
                .then()
                .statusCode(200) // Assert that the status code is 200
                .body("id", equalTo(postId)) // Assert that the post ID matches
                .body("userId", equalTo(1)); // Assert that the user ID is 1
    }

    @Test
    @DisplayName("Create a New Post")
    @Description("This test verifies that we can create a new post.")
    public void testCreatePost() {
        // Create a new post
        String requestBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        Response response = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/posts");

        // Validate the response
        response.then()
                .statusCode(201) // Assert that the status code is 201 (Created)
                .body("title", equalTo("foo")) // Assert that the title matches
                .body("body", equalTo("bar")) // Assert that the body matches
                .body("userId", equalTo(1)); // Assert that the user ID is 1
    }

    @Step("Log the response")
    private void logResponse(Response response) {
        System.out.println("Response: " + response.asString());
    }
}