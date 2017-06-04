package serzh.com.selenium.tests;

import com.jayway.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
/**
 * @author sergii.zagryvyi on 04.06.2017
 */
public class HttpResponseCode {


    WebDriver driver;
    Response response;

    public void checkBrokenLinks() {
        driver = new FirefoxDriver();
        driver.get("http://www.testingexcellence.com");

        //Get all the links on the page
        List<WebElement> links = driver.findElements(By.cssSelector("a"));

        String href;

        for(WebElement link : links) {
            href = link.getAttribute("href");
            response = given().get(href).then().extract().response();

            if(200 != response.getStatusCode()) {
                System.out.println(href + " gave a response code of " + response.getStatusCode());
            }
        }
    }

    public static void main(String args[]) {
        new HttpResponseCode().checkBrokenLinks();
    }


   /* public void checkHttpResponseCode(String url) {
        Response response = given()
                .get(url)
                .then().extract().response();

        System.out.println(response.getStatusCode());
    }

    public static void main(String args[]) {
        new HttpResponseCode().checkHttpResponseCode("http://www.google.com");
    }*/
}
