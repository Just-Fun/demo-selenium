package serzh.com.selenium.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import serzh.com.config.WebDriverConfig;
import serzh.com.selenium.pages.SearchPage;

/**
 * Created by Serzh on 6/3/17.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebDriverConfig.class})
@TestPropertySource(value = {"classpath:selenium.yml"})
public class SearchPageTest {

    private SearchPage searchPage;

    @Autowired
    private WebDriver driver;

    @Autowired
    public void configure(){
        this.searchPage = new SearchPage(driver);
    }

    @Value("${main_page}")
    private String main_page;


    @Before
    public  void setup() {
        driver.get(main_page);
    }

    @Test
    public void titleTest() {
        Assert.assertEquals("DimeToGo", driver.getTitle());
    }

    @Test
    public void englishLanguageTest() {
        searchPage.setLanguage(SearchPage.ENG);
        Assert.assertEquals("English",searchPage.getPageLanguage());
        Assert.assertEquals("My order",searchPage.getMyOrderIconTextValue());
        Assert.assertEquals("travel profitable",searchPage.getHomePageTitleTextValue());
        Assert.assertEquals("ROUND TRIP",searchPage.getRoundTripButtonTextValue());
        Assert.assertEquals("ONE WAY",searchPage.getOneWayButtonTextValue());
        Assert.assertEquals("from",searchPage.getStartPointInputDefaultValue());
        Assert.assertEquals("to",searchPage.getEndPointInputDefaultValue());
        Assert.assertEquals("when",searchPage.getWhenDateInputDefaultValue());
        Assert.assertEquals("1 passenger",searchPage.getPassengersQuantityDefaultValue());
        Assert.assertEquals("search",searchPage.getSearchButtonTextValue());
    }

    @Test
    public void ukrainianLanguageTest() throws InterruptedException {
        searchPage.setLanguage(SearchPage.UKR);
        Assert.assertEquals("Українська",searchPage.getPageLanguage());
        Assert.assertEquals("Моє замовлення",searchPage.getMyOrderIconTextValue());
        Assert.assertEquals("подорожуйте вигідно",searchPage.getHomePageTitleTextValue());
        Assert.assertEquals("ТУДИ Й НАЗАД",searchPage.getRoundTripButtonTextValue());
        Assert.assertEquals("В ОДИН БІК",searchPage.getOneWayButtonTextValue());
        Assert.assertEquals("з",searchPage.getStartPointInputDefaultValue());
        Assert.assertEquals("до",searchPage.getEndPointInputDefaultValue());
        Assert.assertEquals("коли",searchPage.getWhenDateInputDefaultValue());
        Assert.assertEquals("1 пасажир",searchPage.getPassengersQuantityDefaultValue());
        Assert.assertEquals("знайти",searchPage.getSearchButtonTextValue());
    }

    @Test
    public void russianLanguageTest() {
        searchPage.setLanguage(SearchPage.RUS);
        Assert.assertEquals("Русский",searchPage.getPageLanguage());
        Assert.assertEquals("Мой заказ",searchPage.getMyOrderIconTextValue());
        Assert.assertEquals("путешествуй выгодно",searchPage.getHomePageTitleTextValue());
        Assert.assertEquals("ТУДА - ОБРАТНО",searchPage.getRoundTripButtonTextValue());
        Assert.assertEquals("В ОДНУ СТОРОНУ",searchPage.getOneWayButtonTextValue());
        Assert.assertEquals("откуда",searchPage.getStartPointInputDefaultValue());
        Assert.assertEquals("куда",searchPage.getEndPointInputDefaultValue());
        Assert.assertEquals("когда",searchPage.getWhenDateInputDefaultValue());
        Assert.assertEquals("1 пассажир",searchPage.getPassengersQuantityDefaultValue());
        Assert.assertEquals("найти",searchPage.getSearchButtonTextValue());
    }

    @Test
    public void searchKievTest(){
        searchPage.setLanguage(SearchPage.ENG);
        searchPage.enterStartPointInput("Kiev");
        Assert.assertEquals("Kiev, Ukraine",searchPage.getStartPointFieldValue());
    }

    @Test
    public void searchKievThreeLettersTest(){
        searchPage.setLanguage(SearchPage.ENG);
        searchPage.enterStartPointInput("Kie");
        Assert.assertEquals("Kiev, Ukraine",searchPage.getStartPointFieldValue());
    }

    @Test
    public void searchTwoLettersNothingTest(){
        searchPage.setLanguage(SearchPage.ENG);
        searchPage.enterStartPointInput("Ki");
        Assert.assertEquals("Ki",searchPage.getStartPointFieldValue());
    }



}
