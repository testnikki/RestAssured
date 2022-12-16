package createProject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;

public class entToEndPpiTestProject {
    // In testNG what is one of the anotation that allow us to run the test before each of the test
    String memberOf = "/workspaces/member-of";
    String Id, userId;
    Map<String, String> variables;
    Response response;

    @BeforeTest
    public String token()
    {
        RestAssured.baseURI = "https://api.octoperf.com";
        String path = "/public/users/login";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", "testn2005@gmail.com");
        map.put("password", "4testing1234");

        return RestAssured.given()
                .queryParams(map)
                .when()
                .post(path)
                .then()
                .statusCode(SC_OK)
                .extract() //Method that extracts the response JSON DATA
                .body() // Body extracted as a JSON format
                .jsonPath() // Navigate using jsonPath
                .get("token"); // Get the value as a key token
    }

    @Test
    public void memberOf()
    {
        Response response = RestAssured.given()
                .header("Authorization", token())
                .when()
                .get(memberOf)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response();

        Assert.assertEquals(SC_OK, response.statusCode());

        // TASK: add assertion for name
        Assert.assertEquals("TestWorkspaceForhomework2D", response.jsonPath().getString("name[1]"));

        Id = response.jsonPath().getString("id[1]");
        userId = response.jsonPath().getString("userId[1]");

        System.out.println(Id);
        System.out.println(userId);

        // Id and userId ;( are not accessible ina  different Test+
        // What else can I use so I can access those values in other Test
        // 1 - POJOS

        variables = new HashMap<String, String>();
        variables.put("id", Id);
        variables.put("userID", userId);

    }

    @Test(dependsOnMethods = {"memberOf"})
    public void createProject()
    {
        System.out.println(variables.get("id"));
        System.out.println(variables.get("userID"));
        // Provide Json Payload in String format
        String requestBody = "{\"id\":\"\",\"created\":\"2022-11-09T01:58:29.666Z\",\"lastModified\":\"2001-11-09T01:58:29.666Z\",\"userId\":\""
                + variables.get("userID") + "\",\"workspaceId\":\""
                + variables.get("id") + "\",\"name\":\"I did not get the shoes\",\"description\":\"testProject\",\"type\":\"DESIGN\",\"tags\":[]}";

        System.out.println(requestBody);
        response = RestAssured.given()
                .headers("Content-type", "application/json")
                .header("Authorization", token())
                .and()
                .body(requestBody)
                .when()
                .post("/design/projects")
                .then().log().all()
                .extract()
                .response();
        //Task Assert Name and Description:
        Assert.assertEquals("I did not get the shoes", response.jsonPath().getString("name"));
        Assert.assertEquals("testProject", response.jsonPath().getString("description"));
        System.out.print(response.jsonPath().getString("id"));
        variables.put("projectId", response.jsonPath().getString("id"));

    }

    @Test(dependsOnMethods = {"memberOf", "createProject"})
    public void updateProject()
    {
        String updateProject = "{\"id\":\"" + variables.get("projectId")
                + "\",\"created\":\"2022-11-09T01:58:29.666Z\",\"lastModified\":\"2001-11-09T01:58:29.666Z\",\"userId\":\""
                + variables.get("userID") + "\",\"workspaceId\":\"" + variables.get("id")
                + "\",\"name\":\"I GOT THE SHOES :)\",\"description\":\"testProject\",\"type\":\"DESIGN\",\"tags\":[]}";

        System.out.println(updateProject);

        RestAssured.given()
                .header("Content-type", "application/json")
                .header("Authorization", token())
                .and()
                .body(updateProject)
                .when()
                .put("/design/projects/" + variables.get("projectId"))
                .then()
                .log().all();

    }
}
