package services;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

// Here i've created a test with using data provider, checking if Petdetail service is sending the expected statuscodes for given petIds.
public class TestWithDataProvider {


    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider() {
        return new Object[][]{
                {2,200},
                {1,200}

        };


    }


    @Test(dataProvider = "dataProvider")
    public void petDetail(int petId,int statusCode){

            given()
                    .log().all().
                    when()
                    .get("https://petstore.swagger.io/v2/pet/" + petId).
                    then()
                    .statusCode(statusCode)
                    .log().all();

        }
    }

