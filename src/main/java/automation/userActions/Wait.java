package automation.userActions;

import automation.core.UserAction;
import automation.factory.Driver;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static automation.constants.Environment.ELEMENT_TO_BE_CLICKABLE_WAIT_TIME;
import static automation.constants.Environment.FIND_ELEMENT_WAIT_TIME;

public class Wait {

    private static Logger log = Logger.getLogger(Wait.class);

    public static void waitForAjaxCall() {
        log.debug("Waiting for page to get loaded..");
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(FIND_ELEMENT_WAIT_TIME)).
                ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).
                until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0"));
        log.debug("Page loaded successfully");
    }

    public static void waitForPageLoad() {
        log.debug("Waiting for page to get loaded..");
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(FIND_ELEMENT_WAIT_TIME)).
                ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).
                until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        log.debug("Page loaded successfully");
    }

    public static void waitToBeClickable(By locator){
        log.debug("Waiting for element to be clickable "+locator);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(ELEMENT_TO_BE_CLICKABLE_WAIT_TIME));
        wait.ignoring(WebDriverException.class).ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        log.debug("Element has become clickable");
    }

    public static void waitToBeClickable(WebElement element){
        log.debug("Waiting for element to be clickable "+element);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(ELEMENT_TO_BE_CLICKABLE_WAIT_TIME));
        wait.ignoring(WebDriverException.class).ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        log.debug("Element has become clickable");
    }

    public static void waitForElementToDisappear(By locator){
        log.debug("Waiting for element to dissappear "+locator);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(ELEMENT_TO_BE_CLICKABLE_WAIT_TIME));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        log.debug("Element has disappeared");
    }

    public static void waitForElementPresent(WebElement element){
        log.debug("Waiting for element to be present "+element);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(ELEMENT_TO_BE_CLICKABLE_WAIT_TIME));
        wait.ignoring(WebDriverException.class).ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
        log.debug("Element has become visible");
    }
}
