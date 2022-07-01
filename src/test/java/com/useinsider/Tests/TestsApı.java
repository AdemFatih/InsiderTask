package com.useinsider.Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.junit.Assert.assertThrows;

public class TestsApı {
    Object petId;

    @BeforeMethod
    public void ApisetUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }


    @Test(priority = 0)
    public void new_pet_positive() {
        //create a new pet and verify created successfully

        String body = "{\"id\":0,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"newdog\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(body)
                .when().post("/pet");
        petId = response.path("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertEquals(response.path("name"), "newdog");
        System.out.println("petId = " + petId);
    }

    @Test(priority = 1)
    public void new_pet_negative() {
        //can not create a new pet and verify created unsuccessfully
        //according to swagger document in order to retrieve 405 status code, I didn't send a body

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .when().post("/pet");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
        System.out.println("petId = " + petId);
    }

    @Test(priority = 2)
    public void update_positive() {
        //update an existing pet verify updated successfully
        String updatedBody = "{\"id\":" + petId + ",\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"updatedDogName\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(updatedBody)
                .when().put("/pet");

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertEquals(response.path("name"), "updatedDogName");
        Assert.assertEquals(response.path("id"), petId);
    }

    @Test(priority = 3)
    public void update_negative() {
        //can not update an existing pet and verify updated unsuccessfully
        String updatedBody = "{{\"id\":0,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"updatedDogName\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";

        HttpResponseException exception = assertThrows(HttpResponseException.class, () -> RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(updatedBody)
                .when().put("/pet"));

        Assert.assertEquals(exception.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
        Assert.assertEquals(exception.getMessage(), "Bad Request");
    }

    @Test(priority = 4)
    public void find_positive() {
        //find pet by ID and verify get successfully
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .and().pathParams("petId", petId)
                .when().get("/pet/{petId}");

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertEquals(response.path("name"), "updatedDogName");
        Assert.assertEquals(response.path("id"), petId);
    }

    @Test(priority = 5)
    public void find_negative() {
        //can not find pet by ID and verify get unsuccessfully
        HttpResponseException exception = assertThrows(HttpResponseException.class, () -> RestAssured.given().contentType(ContentType.JSON)
                .and().pathParams("petId", "12321234323")
                .when().get("/pet/{petId}"));

        Assert.assertEquals(exception.getStatusCode(), HttpStatus.SC_NOT_FOUND);
        Assert.assertEquals(exception.getMessage(), "Not Found");
    }

    @Test(priority = 6)
    public void deletes_positive() {
        //deletes a pet and verify deleted successfully
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .and().pathParams("petId", petId)
                .when().delete("/pet/{petId}");

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertEquals(response.path("message"), String.valueOf(petId));
        response.prettyPrint();
    }

    @Test(priority = 7)
    public void deletes_negative() {
        //can not deletes a pet and verify deleted unsuccessfully
        HttpResponseException exception = assertThrows(HttpResponseException.class, () -> RestAssured.given().contentType(ContentType.JSON)
                .and().pathParams("petId", "22")
                .when().delete("/pet/{petId}"));

        Assert.assertEquals(exception.getStatusCode(), HttpStatus.SC_NOT_FOUND);
        Assert.assertEquals(exception.getMessage(), "Not Found");

    }
}
