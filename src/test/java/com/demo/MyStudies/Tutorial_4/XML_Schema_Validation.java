package com.demo.MyStudies.Tutorial_4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;

public class XML_Schema_Validation {

    private static FileInputStream fis;
    private static String postBody;
    private static final String baseURI = "http://www.dneonline.com/";
    private static final String basePath = "/calculator.asmx";

    static {
        try {

            File file = new File("src/test/resources/XML_Files/CalculatorADD.xml");
            fis = new FileInputStream(file);
            postBody = IOUtils.toString(fis, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validateXML() {
        

        given()
            .contentType("text/xml")
            .accept(ContentType.XML).body(postBody)
        .when()
            .baseUri(baseURI)
            .post(basePath)
        .then()
            .statusCode(200)
            .log().body()
        .and()
            .body("//*:AddResult.text()", equalTo("8"))
            .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("XML_Files/CalculatorADD_XML_Validator.xsd"));

    }

    @Test
    public void testFileOnClasspath() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("XML_Files/CalculatorADD_XML_Validator.xsd");
        System.out.println("Resource Path: " + (resource != null ? resource.getPath() : "Not Found"));
        assertNotNull("XSD file not found on classpath!", resource);
    }

    @AfterAll
    public static void close() {
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
