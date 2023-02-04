package org.example;

import files.PayLoad;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
    @Test
    public void sumOfCourses() {
        //Verify if Sum of all Course prices matches with Purchase Amount
        JsonPath js = new JsonPath(PayLoad.CoursePrice());
        int countOfCourses = js.getInt("courses.size()");

        int amount = 0;
        for (int i = 0; i < countOfCourses; i++) {
            String name = js.getString("courses[" + i + "].title");
            int price = js.getInt("courses[" + i + "].price");
            int copies = js.getInt("courses[" + i + "].copies");
            System.out.println(name + " --> " +price*copies);
            amount += price*copies;

        }
        System.out.println(amount);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(amount,purchaseAmount);



    }
}
