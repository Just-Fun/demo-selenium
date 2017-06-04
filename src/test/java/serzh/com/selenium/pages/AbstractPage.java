package serzh.com.selenium.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Serzh on 6/3/17.
 */
public abstract class AbstractPage {

    private WebDriver driver;

    protected WebDriverWait webDriverWait;

    private WebDriverWait webDriverWaitOneSecond;

    public AbstractPage(WebDriver driver, int timeOutInSeconds) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, timeOutInSeconds);
        webDriverWaitOneSecond = new WebDriverWait(driver, timeOutInSeconds);
    }

    protected void waitForOneSecond() {
        try {
            webDriverWaitOneSecond.until(ExpectedConditions.titleIs("This condition never could be"));
        } catch (TimeoutException ex) {
            //do nothing
        }
    }
}
