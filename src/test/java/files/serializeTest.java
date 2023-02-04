package files;

import Pojo.AddPlace;
import Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class serializeTest {
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

       Response res =  given().log().all().queryParam("key","qaclick123")
                .body(p)
                .when().post("/maps/api/place/add/json", new Object[0])
                .then().assertThat().statusCode(200).extract().response();
       String responseString = res.asString();
        System.out.println(responseString);

    }
}
