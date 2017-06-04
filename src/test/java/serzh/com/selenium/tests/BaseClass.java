package serzh.com.selenium.tests;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author sergii.zagryvyi on 04.06.2017
 */
@Slf4j
public class BaseClass {

    public static WebDriver cloneDriver;
    public static WebDriver driver;

    String chrome = "chrome";

    protected String user_dir = System.getProperty("user.dir");
    protected Properties prop = new Properties();

    //    protected static final log log = Logger.getLogger(BaseClass.class);
    static {
        DOMConfigurator.configure("src/main/java/Resources/Log4j/log4j.xml");
    }


    public static void driverCopy() {
        cloneDriver = driver;
    }

    public static WebDriver getClonnedDriver() {
        return cloneDriver;
    }

    public static int rowNumber(int row) {
        return row;
    }

    public String getProperty(String configItemName) {
        try {
            FileInputStream f = new FileInputStream(user_dir + "\\src\\main\\java\\Resources\\Properties\\selenium.yml");
            prop.load(f);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return prop.getProperty(configItemName);
    }

    public void startSession() {
        if (getProperty("browser").equals(chrome)) {
            System.setProperty("webdriver.chrome.driver", user_dir + "\\src\\ExternalJars\\chromedriver_win_23.0.1240.0\\chromedriver.exe");
            driver = new ChromeDriver();
            log.info("Chrome Browser called");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        log.info("Opening the Base URL");
        driver.get(getProperty("baseurl_qa"));


        log.info("Clicking on the Sign In Link");
        driver.findElement(By.cssSelector(getProperty("sign_in_link"))).click();

        log.info("Logging In with Username and Password");
        driver.findElement(By.name(getProperty("username"))).sendKeys(getProperty("uname"));
        driver.findElement(By.name(getProperty("password"))).sendKeys(getProperty("pwd"));

        log.info("Clicking on Submit Button");
        driver.findElement(By.xpath(getProperty("submit_button"))).click();

        log.info("Clicking on the Control Panel");
        driver.findElement(By.linkText("Control Panel")).click();

        log.info("Clicking on UMP ADMIN Link");
        driver.findElement(By.linkText("UMP Admin")).click();
        log.info("Opened the UMP Admin site");


    }

    public boolean verifyTextPresent(String value) {
        return driver.getPageSource().contains(value);
    }

}
