package automation.pages.components;

import automation.factory.Driver;
import automation.utitilites.UserAction;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import static automation.constants.Environment.FIND_ELEMENT_WAIT_TIME;
import static automation.utitilites.UserAction.*;

public class SearchBox {

    private static Logger log = Logger.getLogger(SearchBox.class);

    public SearchBox() {
        PageFactory.initElements(Driver.getDriver(), this);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(searchBox));
    }

    @FindBy(xpath = "//select[@id='searchDropdownBox']")
    private WebElement searchCategory;

    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    private WebElement searchBox;

    @FindBy(id = "nav-flyout-searchAjax")
    private WebElement searchSuggestions;

    By searchContainerElements = By.xpath("//div[@class='s-suggestion-container']/div");

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchSubmitBtn;

    public void selectSearchCategory(String searchCategoryTxt) {
        UserAction.selectDropDownByVisibleText(searchCategory, searchCategoryTxt);
    }

    public void searchForItem(String searchItemTxt){
        UserAction.sendKeys(searchBox, searchItemTxt);
        boolean isExceptionThrown = true;
        while(true && isExceptionThrown) { //StaleElementException occurs if the search results got updated. So we retry
            isExceptionThrown = false;
            try {
                List<WebElement> searchSuggestions = Driver.getDriver().findElements(searchContainerElements);
                if(searchSuggestions.size() == 0) throw new StaleElementReferenceException("search results did not appear");
                log.info("Search results size : " + searchSuggestions.size());
                for (WebElement element : searchSuggestions) {
                    log.info("Search suggestions : " + element.getAttribute("aria-label"));
                    if (element.getAttribute("aria-label").equalsIgnoreCase(searchItemTxt)) {
                        UserAction.click(element);
                        break;
                    }
                }
            } catch (StaleElementReferenceException e) {
                isExceptionThrown = true;
                log.info("Auto suggested results got updated. Hence refetching the result...");
            }
        }
    }
}
