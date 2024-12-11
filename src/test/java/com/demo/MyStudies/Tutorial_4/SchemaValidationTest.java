package com.demo.MyStudies.Tutorial_4;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SchemaValidationTest {
    

    @Test
    @DisplayName("Schema Validation Test")
    public void test_1_Get() {

        given()
            .baseUri("https://reqres.in")
        .when()
            .get("/api/users?page=2")
        .then()
            .statusCode(200)
            .assertThat().body(matchesJsonSchemaInClasspath("reqresGetUsersSchema.json"));

    }
}
