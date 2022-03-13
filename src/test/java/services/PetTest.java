package services;

import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


// Here , I've created a test which checks the Pet Create and checks the created pet's detail and deletes it. (Used BeforeClass - Test - AfterClass)

public class PetTest {

    @BeforeClass
    public void postCreatePet() {

        String postData = "{\n" +
                "  \"id\": 4,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"dog\"\n" +
                "  },\n" +
                "  \"name\": \"dogidog\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"dog\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given()
                .body(postData).
                contentType(ContentType.JSON).
                when().post("https://petstore.swagger.io/v2/pet").
                then()
                .statusCode(200)
                .log().all();

    }

    @Test
    public void getPetDetail(){
        int Id = 4;
        given()
                .log().all().
                when()
                .get("https://petstore.swagger.io/v2/pet/" + Id).
                then()
                .statusCode(200)
                .log().all();

    }

    @AfterClass
    public void deletePet(){
        int Id= 4;
        given()
                .log().all()
                .header("api_key","special-key").
                when()
                .delete("https://petstore.swagger.io/v2/pet/" +Id).
                then()
                        .statusCode(200)
                        .log().all();
    }

}
