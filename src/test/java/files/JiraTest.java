package files;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.given;

public class JiraTest {
    public static void main(String[] args) {
        //10005
        RestAssured.baseURI = "http://localhost:8080";
        //Login Scenario
        SessionFilter session= new SessionFilter();

        String response = given().header("Content-Type", "application/json")
                .body("{ \"username\": \"msozturk12\", \"password\": \"Yujkılop32\" }")
                .log().all().filter(session).when().post("/rest/auth/1/session").then().log().all().extract().response().asString();

        given().pathParams("key", "10005").log().all().header("Content-Type", "application/json").body("{\n" +
                "    \"body\": \"Hey I have commented from REST API\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all()
                .assertThat().statusCode(201);
    }
}
