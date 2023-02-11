package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ExcelDriven {


    @Test
    public void addBook() throws IOException {
        dataDriven d = new dataDriven();
        ArrayList<String> data = d.getData("RestAssured","RestAddbook");

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", data.get(1));
        map.put("isbn", data.get(2));
        map.put("aisle", data.get(3));
        map.put("author", data.get(4));

		/*
		HashMap<String,Object> map2 = new HashMap<>();
		map2.put("lat","12");
		map2.put("lng","1as2");
		map.put("location",map2);
	    */

        RestAssured.baseURI = "http://216.10.245.166";

        String resp = given().
                header("Content-Type", "application/json").
                body(map).
                when().log().all().
                post("/Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).and().
                extract().response().asString();


        JsonPath js = ReUsableMethods.rawToJson(resp);
        String id = js.get("ID");
        System.out.println(id);


    }


}
