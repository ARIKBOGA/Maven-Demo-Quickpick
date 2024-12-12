package com.demo.MyStudies.Tutorial_4;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

public class SOAP_API_XML_Test {

    private static FileInputStream fis;
    private static String postBody;

    static{
        try{

            File file = new File("src/test/resources/XML_Files/CalculatorADD.xml");
            fis = new FileInputStream(file);
            postBody = IOUtils.toString(fis,"UTF-8");
    
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void validateSOAP_XML() {

        baseURI = "http://www.dneonline.com/";

        given()
            .contentType("text/xml")
            .accept(ContentType.XML)
            .body(postBody)
        .when()
            .post("/calculator.asmx")
        .then()
            .statusCode(200)
            .body("//*:AddResult.text()", equalTo("8"))
            .log().all();

    }

    @AfterAll
    public static void close(){
        try{
            fis.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
