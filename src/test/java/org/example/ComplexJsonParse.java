package org.example;

import files.PayLoad;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJsonParse {
    public static void main(String[] args) {

        JsonPath js = new JsonPath(PayLoad.CoursePrice());

        //1. Print No of courses returned by API
        int countOfCourses = js.getInt("courses.size()");
        System.out.println(countOfCourses);

        //Print Purchase Amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        //Print Title of the first course
        String titleOfFirstCourse = js.getString("courses[0].title");
        System.out.println(titleOfFirstCourse);

        // Print All course titles and their respective Prices

        for (int i = 0; i < countOfCourses; i++) {
            String courseTitle = js.get("courses[" + i + "].title");
            System.out.println(courseTitle);

            System.out.println(js.get("courses[" + i + "].price").toString());
        }

        //Print no of copies sold by RPA Course

        System.out.println("Print no of copies sold by RPA Course");
        for (int i = 0; i < countOfCourses; i++) {
            String courseTitle = js.get("courses[" + i + "].title");
            if(courseTitle.equalsIgnoreCase("RPA")){
                int copies = js.get("courses[" + i + "].copies");
                System.out.println(copies);
                break;
            }

        }



    }
}
