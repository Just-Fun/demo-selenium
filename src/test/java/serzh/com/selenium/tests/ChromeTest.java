package serzh.com.selenium.tests;

import junit.framework.TestCase;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

/**
 * @author sergii.zagryvyi on 03.06.2017
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class ChromeTest extends TestCase {

    private static ChromeDriverService service;
    private WebDriver driver;

    @BeforeClass
    public static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable((new File("src/test/resources/selenium/ios/chromedriver")))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    @Before
    public void createDriver() {
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void testGoogleSearch() {
        driver.get("http://www.google.com");

        Assert.assertEquals("Google", driver.getTitle());
        // rest of the test...
    }

    @Test
    public void testDimeTogoSearch() {
        driver.get("http://www.google.com");

        Assert.assertEquals("Google", driver.getTitle());
        // rest of the test...
    }
}
