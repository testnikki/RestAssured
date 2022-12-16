package api_testing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.logInPojos;



public class logInWithPojos
{
    logInPojos pojo = new logInPojos();
    @Test
    public void loginPojos() {
        pojo.setUserName("testn2005@gmail.com");
        pojo.setPassWord("4testing1234");

        RestAssured.baseURI = "https://api.octoperf.com";
        String path = "/public/users/login";

        RestAssured.given()
                .queryParam("username", pojo.getUserName())
                .queryParam("password", pojo.getPassWord())
                .when()
                .post(path)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .log().all();
    }

}
