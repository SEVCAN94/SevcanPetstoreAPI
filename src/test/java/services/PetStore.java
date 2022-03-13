package services;

import Models.CreateUserRequest;
import Models.OrderRequest;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PetStore {

    
    //GET request with BDD syntax which checks the inventory api with statuscode 200 check and time assertion.


    @Test
    public void getInventory(){
        given()
                .log().all().
                when()
                .get("https://petstore.swagger.io/v2/store/inventory").
                then()
                .statusCode(200).time(lessThan(2000L))
                .log().all();

    }

    //checks the PetDetail api with statuscode 200 , petID,status,category.Id assertion
    @Test
    public void getPetDetail(){
        int petId = 4;
        given()
                .log().all().
                when()
                .get("https://petstore.swagger.io/v2/pet/" + petId).
                then()
                .statusCode(200)
                .body("id",equalTo(petId))
                .body("status", startsWith("so"))
                .body("category.id",equalTo(4))
               // .body("tags[0].id",equalTo(0))
                .log().all();

    }
    //POST request - Creating a user with using a constructor while sending the body and checking if  the response'S statuscode is 200
    @Test
    public void createUser(){
        CreateUserRequest createUserRequest = new CreateUserRequest(100,"sevcan01","sevcan","egretli","sevcane94@gmail.com","qweqwe","132456789",0);
        String request = new Gson().toJson(createUserRequest);

        given()
                .body(request)
                .contentType(ContentType.JSON)
                .log().all().
                when()
                .post("https://petstore.swagger.io/v2/user").
                then()
                .statusCode(200)
                .log().all();

    }
    //POST request - Creating a pet order with using a constructor while sending the body and Content-Type header with application/json and checking if  the response's statuscode is 200
    @Test
    public void postOrder(){
        OrderRequest orderRequest = new OrderRequest(2,20,1,"2022-03-09T18:53:11.229Z","placed",true);
        String request = new Gson().toJson(orderRequest);

        given()
                .log().all().header("Content-Type","application/json")
                .body(request).
                when().post("https://petstore.swagger.io/v2/store/order").
                then()
                .statusCode(200)
                .log().all();
    }

    //POST request - updating a pet with using queryparams while sending the body and checking if  the response's statuscode is 200
    @Test
    public void postUpdatePet(){
        int petId = 6;

        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("name","doggiee");
        queryParams.put("status","available");

        given()
                .log().all()
                .queryParams(queryParams).
                when()
                .post("https://petstore.swagger.io/v2/pet/" + petId).
                then()
                .statusCode(200)
                .log().all();
    }

}
