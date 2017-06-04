package serzh.com.selenium.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Created by Serzh on 6/3/17.
 */
@Component
public class SearchPage extends AbstractPage {

    public final static String ENG = "English";
    public final static String UKR = "Українська";
    public final static String RUS = "Русский";


    public SearchPage(WebDriver driver) {
        super(driver, 5);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "div[class='main-header_link main-header-language dropdown-trigger']")
    @CacheLookup
    private WebElement languageTrigger;

    @FindBy(how = How.CSS, using = "div[class='dropdown-list']")
    @CacheLookup
    private WebElement languageValues;

    @FindBy(how = How.CSS, using = "div[class='dropdown-list-item']")
    @CacheLookup
    private List<WebElement> languages;

    @FindBy(how = How.CSS, using = "h1[class='home-page-section_title']")
    @CacheLookup
    private WebElement homePageTitle;

    @FindBy(how = How.CSS, using = "button[class='roundtrip-button']")
    @CacheLookup
    private WebElement roundTripButton;

    @FindBy(how = How.CSS, using = "button[class='oneway-button is-active']")
    @CacheLookup
    private WebElement oneWayButton;

    @FindBy(how = How.CSS, using = "input[class='from-input']")
    @CacheLookup
    private WebElement startPointInput;

    @FindBy(how = How.CSS, using = "input[class='to-input']")
    @CacheLookup
    private WebElement endPointInput;

    @FindBy(how = How.CSS, using = "input[class='DateInput__input']")
    @CacheLookup
    private WebElement whenDateInput;

    @FindBy(how = How.CSS, using = "span[class='passengers-quantity']")
    @CacheLookup
    private WebElement passengersQuantityInput;

    @FindBy(how = How.CSS, using = "input[class='search-submit']")
    @CacheLookup
    private WebElement searchButton;

    @FindBy(how = How.CSS, using = "div[class='main-header_link main-header_my-order dropdown-trigger']")
    @CacheLookup
    private WebElement myOrderTrigger;


    public SearchPage clickOnLanguageTrigger() {
        languageTrigger.click();
        return this;
    }

    public String getLanguageTriggerValue() {
        return languageTrigger.getText();
    }

    public SearchPage setLanguage(String language) {
        clickOnLanguageTrigger();
        for (WebElement webElement : languages) {
            if (Objects.equals(webElement.getText(), language)) {
                webElement.click();
                webDriverWait.until(ExpectedConditions.textToBePresentInElement(languageTrigger, language));
                waitForOneSecond();
            }
        }
        return this;
    }

    public String getPageLanguage() {
        String allLanguagesString = languageTrigger.getText();
        String[] languages = allLanguagesString.split("\\n");
        return languages[0];
    }

    public String getHomePageTitleTextValue() {
        return homePageTitle.getText();
    }

    public String getRoundTripButtonTextValue() {
        return roundTripButton.getText();
    }

    public String getOneWayButtonTextValue() {
        return oneWayButton.getText();
    }

    public String getStartPointInputDefaultValue() {
        return startPointInput.getAttribute("placeholder");
    }

    public String getStartPointFieldValue(){
        return startPointInput.getAttribute("value");
    }

    public String getEndPointInputDefaultValue() {
        return endPointInput.getAttribute("placeholder");
    }

    public String getWhenDateInputDefaultValue() {
        return whenDateInput.getAttribute("placeholder");
    }

    public String getPassengersQuantityDefaultValue() {
        return passengersQuantityInput.getText();
    }

    public String getSearchButtonTextValue() {
        return searchButton.getAttribute("value");
    }

    public String getMyOrderIconTextValue() {
        return myOrderTrigger.getText();
    }

    public SearchPage enterStartPointInput(String input) {
        String[] inputLetters = input.split("");
        startPointInput.click();
        for (String inputLetter : inputLetters) {
            startPointInput.sendKeys(inputLetter);
        }
        waitForOneSecond();
        startPointInput.sendKeys(Keys.ARROW_DOWN);
        startPointInput.sendKeys(Keys.ENTER);
        return this;
    }

}
