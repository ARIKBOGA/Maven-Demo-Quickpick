package com.demo.MyStudies.Tutorial_4;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Description;

public class TestsOnLocalAPI {

    private static final String baseURI = "https://reqres.in/api";

    @Test
    @DisplayName("Get test case on local API")
    @Description("This test case tests the GET method on the local API.")
    public void test_1_Get() {

        given()
            .baseUri(baseURI)
            .get("/users")
        .then()
            .statusCode(200)
            .log().all();

    }
    

    @SuppressWarnings("unchecked")
    //@Test
    @DisplayName("Post test case on local API")
    @Description("This test case tests the POST method on the local API.")
    public void test_2_Post() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName","Selim");
        jsonObject.put("lastName","Arıkboğa");
        jsonObject.put("subjectId", 2);

        given()
            .baseUri(baseURI)
            .body(jsonObject.toJSONString())
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .log().all();

    }

    @SuppressWarnings("unchecked")
    @Test
    @DisplayName("Put test case on local API")
    @Description("This test case tests the PUT method on the local API.")
    public void test_3_Put() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName","Levent");
        jsonObject.put("lastName","Arıkboğa");
        jsonObject.put("subjectId", 1);

        given()
            .baseUri(baseURI)
            .body(jsonObject.toJSONString())
        .when()
            .put("/users/487")
        .then()
            .statusCode(200)
            .log().all();

    }
}
