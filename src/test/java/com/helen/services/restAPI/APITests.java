package com.helen.services.restAPI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.google.gson.JsonArray;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class APITests {
    String filePath = "src/test/resources/user.yaml";


    @DataProvider(name="testData")
    public Object[][] getTestData()
    {
        return YamlHandle.getTestData(filePath);
    }

    @DataProvider(name="testDataJson")
    public Object[][] getTestDataJson()
    {
        return YamlHandle.getTestData(filePath);
    }

    @Test(dataProvider = "testData")
    public void testGetUser(Map<String,Object> testData) {
        System.out.println(testData.get("email"));
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
       /* given().
                header("Content-Type", "application/json").
                when().
                get("/users/1"). // Endpoint for the GET request
                then().
                statusCode(200). // Assert the status code
                body("id", equalTo(1)). // Validate specific field
                body("username", equalTo("Bret")); // Validate another field*/
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("userId", 1);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("/users/1")
                .then()
                .statusCode(200) // Validate the status code
                .extract() // Extract the response
                .response(); // Capture the full response

        /*String response = RestAssured
                .given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();*/


        JsonObject res = new JsonObject();
        //res.getAsJsonObject().get();
        System.out.println("userName:" + response);
    }
    @Test
    public void testCreateUser() {
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("name", "John Doe");
        requestParams.addProperty("username", "johndoe");
        requestParams.addProperty("email", "johndoe@example.com");

        given().
                header("Content-Type", "application/json").
                body(requestParams.toString()). // Send JSON payload
                when().
                post("/users"). // Endpoint for the POST request
                then().
                statusCode(201). // Assert the status code
                body("name", equalTo("John Doe")); // Validate response body*/
    }
    @Test
    public void testUpdateUser() {
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("name", "John Updated");
        requestParams.addProperty("username", "johnupdated");

        given().
                header("Content-Type", "application/json").
                body(requestParams.toString()). // Send JSON payload
                when().
                put("/users/1"). // Endpoint for the PUT request
                then().
                statusCode(200).// Assert the status code
                body("name", equalTo("John Updated")); // Validate response body*/
    }
    @Test
    public void testPatchUser() {
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("username", "patchedusername");

        given().
                header("Content-Type", "application/json").
                body(requestParams.toString()). // Send JSON payload
                when().
                patch("/users/1"). // Endpoint for the PATCH request
                then().
                statusCode(200). // Assert the status code
                body("username", equalTo("patchedusername")); // Validate response body
    }
    @Test
    public void testWithdraw()
    {
        String url = "/withdrawal/applyWithdrawal_test";

        HashMap<String,String> httpheader = new HashMap<>();
        httpheader.put("content_type","application/json");
        httpheader.put("Accept","*/*");

        JsonArray fileL = new JsonArray();
        fileL.add("/123");

        JsonObject objChild = new JsonObject();
        objChild.addProperty("qAccount",12345);
        objChild.addProperty("accountName","test");
        objChild.addProperty("amount",123);

        JsonArray withdraw = new JsonArray();
        withdraw.add(objChild);

        JsonObject body = new JsonObject();
        body.addProperty("userId",23456);
        body.add("withdrawdto",withdraw);

        String bodyS = body.toString();

        Response res = RestAssured.given().headers(httpheader).body(bodyS).when().post(url).then().statusCode(200).extract().response();

        String token = res.jsonPath().getString("data.token");
        String auth = res.getHeader("cookie");




    }
}
