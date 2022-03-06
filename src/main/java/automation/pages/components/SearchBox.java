package automation.pages.components;

import automation.abstracts.AbstractComponent;
import automation.core.UserAction;
import automation.factory.Driver;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

@Getter
public class SearchBox extends AbstractComponent {

    private static Logger log = Logger.getLogger(SearchBox.class);

    public SearchBox() {
        super(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    }

    By searchCategory = By.xpath("//select[@id='searchDropdownBox']");

    By searchBox = By.xpath("//input[@id='twotabsearchtextbox']");

    By searchSuggestions = By.id("av-flyout-searchAjax");

    By searchSubmitButton = By.id("nav-search-submit-button");

    By searchContainerElements = By.xpath("//div[@class='s-suggestion-container']/div");

    public void selectSearchCategory(String searchCategoryTxt) {
        UserAction.select.selectDropDownByVisibleText(searchCategory, searchCategoryTxt);
    }

    public void searchForItem(String searchItemTxt){
        UserAction.type.sendKeys(searchBox, searchItemTxt);
        int count = 0;
        boolean isElementFound = false;
        while(true && !isElementFound && count < 5) { //StaleElementException occurs if the search results got updated. So we retry
            try {
                Thread.sleep(5000);
                List<WebElement> searchSuggestions = Driver.getDriver().findElements(searchContainerElements);
                if(searchSuggestions.size() == 0) throw new StaleElementReferenceException("search results did not appear");
                log.info("Search results size : " + searchSuggestions.size());
                for (WebElement element : searchSuggestions) {
                    log.info("Search suggestions : " + element.getAttribute("aria-label"));
                    if (element.getAttribute("aria-label").equalsIgnoreCase(searchItemTxt)) {
                        UserAction.move.moveToElement(element);
                        UserAction.click.clickElement(element);
                        isElementFound = true;
                        break;
                    }
                }
            } catch (StaleElementReferenceException | InterruptedException e) {
                log.info("Auto suggested results got updated. Hence refetching the result...");
            }
            count++;
        }
    }
}
