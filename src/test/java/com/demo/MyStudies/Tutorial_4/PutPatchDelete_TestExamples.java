package com.demo.MyStudies.Tutorial_4;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.demo.MyStudies.Hooks.Base;

public class PutPatchDelete_TestExamples extends Base{
    
    @SuppressWarnings("unchecked")
    @Test
    @DisplayName("Put Test Case")
    public void test_1_Put() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Burak");
        jsonObject.put("job", "QA");

        response = given()
            .body(jsonObject.toJSONString())
            .spec(putSpec)
        .when()
            .put()
        .then()
            .statusCode(200)
            .log().body()
            .extract().response();

        Instant updatedAt = Instant.parse(response.jsonPath().get("updatedAt").toString());
        
        assertTrue(ChronoUnit.SECONDS.between(updatedAt, Instant.now()) < 1);
    }


    @SuppressWarnings("unchecked")
    @Test
    @DisplayName("Patch Test Case")
    public void test_2_Patch() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Burak");
        jsonObject.put("job", "Leader");

        response = given()
            .body(jsonObject.toJSONString())
        .when()
            .baseUri(baseURI)
            .patch(patchPath)
        .then()
            .statusCode(200)
            .log().body()
            .extract().response();

    }

    /**
     * This test case verifies that we can delete a user by sending a DELETE request.
     * 
     * The test case sends a DELETE request to the service with the id of the user
     * to be deleted. It then checks if the response status code is 204 and if the
     * response headers contain the Date header with the current date and time.
     */
    @Test
    @DisplayName("Delete Test Case")
    public void test_3_Delete() {

        response = given()
            .when()
            .baseUri(baseURI)
            .delete(deletePath)
        .then()
            .statusCode(204) // Assert that the status code is 204
            .log().all() // Log the response
            .extract().response();

        // Get the date from the response headers
        String date = response.header("Date");

        // Parse the date
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
        Instant deletedAt = Instant.from(formatter.parse(date));

        // Assert that the deletedAt is within the last second
        assertTrue(ChronoUnit.SECONDS.between(deletedAt, Instant.now()) < 1);
    }
}
