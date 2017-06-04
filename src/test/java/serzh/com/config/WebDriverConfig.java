package serzh.com.config;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * @author sergii.zagryvyi on 03.06.2017
 */
@Configuration
public class WebDriverConfig {

    @Bean
    public WebDriver getWebChromeDriver() {
//        if (browserName.equals(CHROME)) {
        String osName = System.getProperty("os.name");
        if (StringUtils.containsIgnoreCase(osName, "mac")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/selenium/ios/chromedriver");
        } else if (StringUtils.containsIgnoreCase(osName, "windows")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/selenium/windows/chromedriver.exe");
        } else {
//            TODO download for 32 or 64 linux?
        }
        return new ChromeDriver();
    }

    @PreDestroy
    void stop() {
        this.getWebChromeDriver().quit();
    }

}
