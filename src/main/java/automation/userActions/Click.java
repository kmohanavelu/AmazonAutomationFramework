package automation.userActions;

import automation.factory.Driver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Click {

    private static Logger log = Logger.getLogger(Click.class);

    public static void clickBtn(By locator){
        Wait.waitForPageLoad();
        Wait.waitToBeClickable(locator);
        Driver.getDriver().findElement(locator).click();
        log.info("Clicked on button using locator "+locator);
    }

    public static void clickBtn(WebElement element){
        Wait.waitForPageLoad();
        Wait.waitToBeClickable(element);
        element.click();
        log.info("Clicked on button using element "+element);
    }


    public static void clickElement(WebElement element){
        Wait.waitForPageLoad();
        try {
            element.click();
        }catch (ElementNotInteractableException e){
            JavascriptExecutor executor = (JavascriptExecutor)Driver.getDriver();
            executor.executeScript("arguments[0].click();", element);
        }
        log.info("Clicked on element "+element);
    }

    public static void clickElement(By locator){
        Wait.waitForPageLoad();
        try {
            Driver.getDriver().findElement(locator).click();
        }catch (ElementNotInteractableException e){
            JavascriptExecutor executor = (JavascriptExecutor)Driver.getDriver();
            executor.executeScript("arguments[0].click();", Driver.getDriver().findElement(locator));
        }
        log.info("Clicked on element by locator "+locator);
    }


    public static void doubleClick(WebElement element){
        Wait.waitForPageLoad();
        Wait.waitToBeClickable(element);
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).doubleClick().build().perform();
        log.info("Double Clicked on "+element);
    }

    /*
     * Sometimes even after clicking the button, the app remains on the same page.
     * To ensure navigation to the next page, included a click for the second time if the same button is still present
     * */
    public static void btnClickWithDissappearCheck(By locator){
        Wait.waitForPageLoad();
        Wait.waitToBeClickable(locator);
        Driver.getDriver().findElement(locator).click();
        Wait.waitForElementToDisappear(locator);
        if(Verify.verifyIfElementPresent(locator)) Driver.getDriver().findElement(locator).click();
        log.info("Clicked on "+locator);
    }
}
