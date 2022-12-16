package api_testing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class LoginTypes
{
    /**
     * Log in to octoperf and use a full url to perform a sign in
     *
     */
    @Test
    public static void testUsingFullPath()
    {
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=wilsonpakkawadee@gmail.com&password=N12345")// entering endpoint parameter
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    // Log in Using Map to Verify contentType and status code
    // Map stores -> key and value pairs Hashmap implement Maps
    @Test
    public void useMapsToLogin()
    {
        RestAssured.baseURI = "https://api.octoperf.com/";
        String path = "public/users/login";// this will read path for the endpoint(https://api.octoperf.com/)

        // Writing Map with String, String values
        Map<String,String> map = new HashMap<String,String>();
        map.put("username","testn2005@gmail.com");
        map.put("password","4testing1234");

        RestAssured.given()
                .queryParam(map.toString())
                .when()
                .post(path)
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .and()
                .statusCode(200);
    }

    /**
     * Log in using query param
     */
    @Test
    public void userParams()
    {
        RestAssured.baseURI = "https://api.octoperf.com/";
        String path = "public/users/login";

        RestAssured.given()
                .queryParam("username", "testn2005@gmail.com")
                .queryParam("password", "4testing1234")
                .when()
                .post(path)
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .and()
                .statusCode(200);

    }
}
