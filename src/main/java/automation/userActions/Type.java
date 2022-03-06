package automation.userActions;

import automation.factory.Driver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static automation.userActions.Wait.*;

public class Type {

    private static Logger log = Logger.getLogger(Type.class);


    public static void sendKeys(By locator, String text){
        Wait.waitForPageLoad();
        Wait.waitForElementPresent(Driver.getDriver().findElement(locator));
        Driver.getDriver().findElement(locator).sendKeys(text);
        log.info("Send keys : "+text+" to WebElement "+locator);
    }
}
