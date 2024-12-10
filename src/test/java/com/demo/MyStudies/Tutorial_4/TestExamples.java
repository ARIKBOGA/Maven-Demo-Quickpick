package com.demo.MyStudies.Tutorial_4;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.*;

import com.demo.MyStudies.Hooks.Base;

import static org.junit.jupiter.api.Assertions.*;

public class TestExamples extends Base {

    @Test
    public void test_1_Get() {

        response = given()
                .when()
                .baseUri(baseURI)
                .get("/users?page=2");
        // .then()
        // .assertThat().statusCode(200)
        // .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getTime());

        // Map<String, Object> map = response.jsonPath().get();
        // map.entrySet()
        // .forEach(System.out::println);

        Assertions.assertEquals(200, response.statusCode());

    }

    @Test
    public void test_2_Get() {

        String path = "/users?page=2";

        //File postBody = new File("src/test/resouces/JsonFiles/PostBody.json");

        response = given()
                .when()
                    .baseUri(baseURI)
                    .get(path)
                .then()
                    .statusCode(200)
                    .body("data[2].id", equalTo(9))
                    .body("data.id", hasItems(7, 8, 9, 10, 11, 12))
                    .log().body()
                    .extract().response();
    }

    /*
    @Test
    public void test_3_CreatedAtInRange() {

        // Get now before the request
        Instant now = Instant.now();

        String path = "/api/users/2";
        String responseCreatedAt = 
            given()
                .baseUri(baseURI)
            .when()
                .post("/api/users/2")
            .then()
                .statusCode(201)
                .extract()
                .path("createdAt"); // get the createdAt 

        // Parse the createdAt timestamp
        Instant createdAt = Instant.parse(responseCreatedAt);


        // check if the createdAt is within the last 3 seconds
        assertTrue(ChronoUnit.SECONDS.between(createdAt, now) < 3);
        
    } 
    */
}
