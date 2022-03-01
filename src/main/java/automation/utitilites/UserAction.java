package automation.utitilites;

import automation.factory.Driver;
import automation.pages.LoginPage;
import org.apache.log4j.Logger;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        element.sendKeys(text);
        log.info("Send keys : "+text+" to WebElement "+element);
    }

    public static void moveToElement(WebElement element){
        waitForPageLoad();
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element);
        log.info("Moved to element : "+element);
    }

    public static void waitForPageLoad() {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(FIND_ELEMENT_WAIT_TIME)).
                ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).
                until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static void waitToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(ELEMENT_TO_BE_CLICKABLE_WAIT_TIME));
        wait.ignoring(WebDriverException.class).ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
