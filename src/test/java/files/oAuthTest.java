package files;

import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;

public class oAuthTest {
    public static void main(String[] args) throws InterruptedException {

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
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWtgzh5kFonF7Cjk_NkNV-qUWd7rMcow9D05Vhbs4NTB7ADolSUqprcNq0KLjZwnaM-eMA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
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


        String response = given().queryParam("access_token", accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/getCourse.php").asString();

        System.out.println(response);

    }
}
