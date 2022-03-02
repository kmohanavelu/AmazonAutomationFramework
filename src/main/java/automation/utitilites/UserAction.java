package automation.utitilites;

import automation.factory.Driver;
import automation.pages.LoginPage;
import org.apache.log4j.Logger;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;

import static automation.constants.Environment.ELEMENT_TO_BE_CLICKABLE_WAIT_TIME;
import static automation.constants.Environment.FIND_ELEMENT_WAIT_TIME;

public class UserAction {

    private static Logger log = Logger.getLogger(UserAction.class);

    public static void click(WebElement element){
        waitForPageLoad();
        waitToBeClickable(element);
        element.click();
        log.info("Clicked on "+element);
    }

    public static void sendKeys(WebElement element, String text){
        waitForPageLoad();
        waitForElementPresent(element);
        element.sendKeys(text);
        log.info("Send keys : "+text+" to WebElement "+element);
    }

    public static void moveToElement(WebElement element){
        waitForPageLoad();
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element);
        log.info("Moved to element : "+element);
    }

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

    public static void selectDropDownByVisibleText(WebElement element, String text){
        waitForPageLoad();
        String firstCharacterDropDownVal = ""+text.charAt(0);
        HashSet<String> uniqueDropDownVals = new HashSet<>();
        try {
            Select select = new Select(element);
            select.selectByVisibleText(text);
        }catch (ElementNotInteractableException e){
            Select select = new Select(element);
            while(!select.getFirstSelectedOption().getText().equalsIgnoreCase(text) &&
                    uniqueDropDownVals.add(select.getFirstSelectedOption().getText())) {
                element.sendKeys(firstCharacterDropDownVal);
            }
        }
        log.info("Selected dropdown by visible text "+text);
    }

    public static void waitToBeClickable(WebElement element){
        log.debug("Waiting for element to be clickable "+element);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(ELEMENT_TO_BE_CLICKABLE_WAIT_TIME));
        wait.ignoring(WebDriverException.class).ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        log.debug("Element has become clickable");
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
