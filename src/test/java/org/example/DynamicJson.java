package org.example;

import files.PayLoad;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    @Test(dataProvider = "BooksData")
    public void addBook(String isbn,String aisle){
        RestAssured.baseURI="http://216.10.245.166";
        String resp =  given().header("Content-Type","application/json")
                .body(PayLoad.AddBook(isbn,aisle)).
                when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = new JsonPath(resp);
        String id = js.get("ID");
        System.out.println(id);
    }
    @DataProvider(name="BooksData")
    public Object[][] getData(){
        //array=collection od elements
        //multidimentionel array = collection of arrays
        //Object[][] asd = {{"ojfwty","9363"},{"cwetee","456"},{"yujkiklop","695"}};

        return new Object[][] {{"ojfwt12y","932123"},{"cds12tee","43612"},{"yujdf12op","612we5"}};


    }
}
