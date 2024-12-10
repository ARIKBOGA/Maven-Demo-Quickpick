package com.demo.MyStudies.Tutorial_4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.demo.MyStudies.Hooks.Base;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class GetAndPost_TestExamples extends Base {

    @Test
    @DisplayName("Get Test Case")
    public void test_1_Get() {

        Response response = given()
            .baseUri(baseURI)
            .get(getPath)
        .then()
            .statusCode(200)
            .body("data[4].first_name", equalTo("George"))
            .body("data.first_name", hasItems("Byron", "Rachel", "George"))
            .extract().response();

    }

    @SuppressWarnings("unchecked")  // It is added to suppress warnings after VS code compilation. 
                                    // Further info look at:
                                    // https://github.com/redhat-developer/vscode-java/issues/1657
    /**
     * Test Case for posting a user to the service.
     * 
     * The test case sends a POST request to the service with a JSON body
     * containing a user data. It then checks if the response status code is
     * 201 and if the response body contains the id of the created user.
     * 
     * Additionally, it checks if the createdAt timestamp in the response body
     * is within the last second.
     */
    @Test
    @DisplayName("Post Test Case")
    public void test_2_Post(){
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Burak");
        jsonObject.put("job", "QA");

        ValidatableResponse response = given()
            .body(jsonObject.toJSONString())
            .spec(postSpec)
        .when()
            .post()
        .then()
            .statusCode(201)
            .body("id", is(notNullValue()))
            .log().body();


        // check if the createdAt is within the last 1 seconds
        Instant now = Instant.now();
        Instant createdAt = Instant.parse(response.extract().jsonPath().getString("createdAt"));

        assertTrue(ChronoUnit.SECONDS.between(createdAt, now) < 1);
    }

}