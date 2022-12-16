package api_testing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class _1_introductionAPI
{
    // Rest Assured is a java library that is used to perform API tests

    // It uses BDD style such as given, when, then, and ...

    // Rest Assured has methods to fetch data from the response body when we make a request

    // As part of Rest Assured we also perform CRUD operations: CREATE, READ, UPDATE, DELETE __> (POST, GET, PUT, DELETE)

    // As test Engineers we use most GET, POST if needed we use PUT, and DELETE

    // NOTE: As QA Testers we mostly work with JSON body payload, we also have exposure to xml

    // JSON example:
    // {"name": "TLA"}

    // Some of the most used methods in RestAssured are:
    // When we make a request:
    //      given() --> use to prepare the request
    //      When() --> use to sent the request
    //      Then() --> use to verify the request

    // When we verify a request:
    //      prettyPrint() --> used to print the response in pretty format
    //      prettyPeek() --> used to print response headers, url, in the console in a pretty format
    //      log()         --> logs used to print the response
    //      asString()    --> Allows us to print the string format.
    //      contentType() --> used to verify the content type from the response body and when we send the request
    //      accept()      --> used to verify the response from the head when we make a GET request

    //      baseURI       --> used to verify body response from the header when making a Request.
        public static String baseURI = "https://api.octoperf.com/";
    //      Path          --> when we make a request we only provide the path(endpoint) to the specific baseURI
        private String path = "public/users/login";

    //      Full URI = https://api.octoperf.com/public/users/login

    //      What is an endpoint?
    //      An endpoint is a unique URL that represent the object or collect of objects.

    //      Query Params?
    //            Example: https://www.amazon.com/s?k=christmas+tree
    //                           BaseURI       Endpoint   ? Query params

    // Task: Make a http request: POST for login for octoperf
    // UNIT Tests

    @Test
    public void printResposse()
    {
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=testn2005@gmail.com&password=4testing1234")
                .prettyPeek();
    }

    @Test
    public void prettyPrint()
    {
        RestAssured.given()
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors")
                .prettyPrint();
    }

    @Test
    public void printPeek()
    {
        RestAssured.given()
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors")
                .prettyPeek();
    }

    /**
     *  When we verify the response body we also verify the status codes:
     *  1xx --> Information
     *  2xx --> Success (200 -> OK, 201 -> Created, 204 -> No content)
     *  3xx --> Redirection
     *  4xx --> Client error (400, 401, 403, 404)
     *  5x2xx --> Success
     */

    // Tesk: Validate response status code for login

    @Test
    public void verifyStatusCode()
    {
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=testn2005@gmail.com&password=4testing1234")
                .then()
                .assertThat()
                .contentType(ContentType.JSON);
        System.out.println("Content Type is JSON");
    }

    @Test
    public void verifyJsonAuthor()
    {
        RestAssured.given()
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .and()
                .statusCode(200);
        System.out.println("verify if Content Type is JSON");
    }

    @Test
    public void verifyJsonActivity()
    {
        RestAssured.given()
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Activities")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .and()
                .statusCode(200);
        System.out.println("verify if Content Type is JSON");
    }
}

