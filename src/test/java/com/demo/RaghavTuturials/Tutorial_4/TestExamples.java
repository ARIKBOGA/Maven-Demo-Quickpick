package com.demo.RaghavTuturials.Tutrial_4;

import org.junit.jupiter.api.Test;

import com.demo.RaghavTuturials.Hooks.Base;

import static io.restassured.RestAssured.*;

public class TestExamples extends Base{
    
    
    @Test
    public void test_1(){

        response = given()
            .when()
                .baseUri(baseURI)
                .get("/users?page=2")
            .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getTime());
    }
}
