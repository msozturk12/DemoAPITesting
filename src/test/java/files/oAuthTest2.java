package files;

import Pojo.Api;
import Pojo.GetCourse;
import Pojo.webAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class oAuthTest2 {
    public static void main(String[] args) throws InterruptedException {
        String[] courseTitle = {"Selenium Webdriver Java","Cypress","Protractor"};

        //https://rahulshettyacademy.com/getCourse.php?access_token=ya29.
        // a0AfH6SMD-EytFH41srRFqd55ewFQGIJDcuEq-uhPn4UYeXmyRkY5NY0VBPYZgAk2zou8KRYZpspkrC3QtrhKS5McUsWoxCFpWoYEVFq3rN00eJCicj-_j9xv8Q-9gTumTGy5rJxgfkMrTVZSbtWnB2LSR0C1L

        /*
        4%2F0AWtgzh5H0VNREbYvddPyX-5NW76s3IsxGdj5DVe6PlHDdWDHPIK6EgCYOEam6DtEqTLk8g

         */
        //System.setProperty("webDriver.chrome.driver", "C:\\Users\\mesut\\Desktop\\Grid\\chromeBrowser\\chromedriver");
        //WebDriver driver = new ChromeDriver();
       // driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&state=verifysfsf&service=lso&o2v=2&flowName=GeneralOAuthFlow");
        //driver.findElement(By.xpath("//div[@class='tgnCOd']")).click();
        //Thread.sleep(4000);
       // String url = driver.getCurrentUrl();
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWtgzh4-lrNsf1KleXUw6VI_D12lLnjzRpalE9x-w6dcR7_07g-o4klruzQDfMAzwsvs0g&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];
        System.out.println(code);


        String accessTokenResponse = given().urlEncodingEnabled(false).queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type", "authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();
        JsonPath js = new JsonPath(accessTokenResponse);
        String accessToken = js.getString("access_token");


       GetCourse gc= given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

        System.out.println( gc.getLinkedIn());
        System.out.println( gc.getInstructor());
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());//soapuı

        List<Api> apiCourses = gc.getCourses().getApi();
        for (int i = 0; i <apiCourses.size() ; i++) {
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
                System.out.println( apiCourses.get(i).getPrice());
                gc.getCourses().getMobile().get(0).getCourseTitle();
            }

        }

       List<webAutomation> webAutomations = gc.getCourses().getWebAutomation();
        ArrayList<String> titles=new ArrayList<>();
        for (int i = 0; i <webAutomations.size() ; i++) {
            titles.add(webAutomations.get(i).getCourseTitle());

        }

       List<String> expectedList=Arrays.asList(courseTitle);
        Assert.assertTrue(titles.equals(expectedList));
        //System.out.println(titles.equals(expectedList));

        //System.out.println(gc);

    }
}
