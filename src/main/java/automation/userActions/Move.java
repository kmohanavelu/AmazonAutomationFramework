package automation.userActions;

import automation.factory.Driver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Move {

    private static Logger log = Logger.getLogger(Move.class);
    public static void moveToElement(By locator){
        Wait.waitForPageLoad();
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(Driver.getDriver().findElement(locator)).perform();
        log.info("Moved to element by locator : "+locator);
    }

    public static void moveToElement(WebElement element){
        Wait.waitForPageLoad();
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
        log.info("Moved to element : "+element);
    }
}
