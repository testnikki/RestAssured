package practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class day2
{
    // TODO: send a request and validate Status code and Content-type if any.

    // pet/findPetsByStatus
    @Test
    public void findPetsByStatus()
    {
        RestAssured.given()
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .and()
                .statusCode(200);
        System.out.println("Verify pet status for available pet requests from customers");

    }
    // store/getInventory
    @Test
    public void storeInventory()
    {
        RestAssured.given()
                .when()
                .get("https://petstore.swagger.io/v2/store/inventory")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .and()
                .statusCode(200);
        System.out.println("Verify pet store inventory");
    }

}
