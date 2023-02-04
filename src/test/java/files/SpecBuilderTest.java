package files;

import Pojo.AddPlace;
import Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setName("Frontline house");
        p.setPhone_number("(+91) 983 893 3937");
        p.setAddress("29, side layout, cohen 09");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setLanguage("French-IN");

        List<String> mylist = new ArrayList<>();
        mylist.add("shoe park");
        mylist.add("shop");
        p.setTypes(mylist);

        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        RequestSpecification res = given().spec(req)
                .body(p);

        ResponseSpecification resspec = (new ResponseSpecBuilder()).expectStatusCode(200).expectContentType(ContentType.JSON).build();



        Response response = res.when().post("/maps/api/place/add/json", new Object[0])
                .then().spec(resspec).extract().response();

        String responseString = response.asString();
        System.out.println(responseString);

    }
}
