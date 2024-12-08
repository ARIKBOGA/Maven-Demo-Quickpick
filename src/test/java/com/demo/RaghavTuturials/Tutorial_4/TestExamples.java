package com.demo.RaghavTuturials.Tutorial_4;


import com.demo.RaghavTuturials.Hooks.Base;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class TestExamples extends Base{
    
    
    @Test
    public void test_1(){

        response = given()
            .when()
                .baseUri(baseURI)
                .get("/users?page=2")
            .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.statusCode());
        System.out.println(response.getTime());
    }
}
