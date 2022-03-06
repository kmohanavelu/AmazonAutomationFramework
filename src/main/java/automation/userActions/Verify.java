package automation.userActions;

import automation.factory.Driver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class Verify {

    private static Logger log = Logger.getLogger(Verify.class);

    public static boolean verifyIfElementPresent(By locator){
        log.debug("Checking if element is displayed "+locator);
        try{
            if(Driver.getDriver().findElement(locator).isEnabled()
                    && Driver.getDriver().findElement(locator).isDisplayed()) return true;
            else return false;
        }catch (Exception e){
            log.debug("Element has disappeared");
            return false;
        }
    }
}
