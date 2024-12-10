package com.demo.MyStudies.Tutorial_4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.demo.MyStudies.Hooks.Base;

public class PutPatchDeleteTestExamples extends Base{
    
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

    @Test
    @DisplayName("Delete Test Case")
    public void test_3_Delete() {

        response = given()
            .when()
            .baseUri(baseURI)
            .delete(deletePath)
        .then()
            .statusCode(204)
            .log().body()
            .extract().response();
    }
}
