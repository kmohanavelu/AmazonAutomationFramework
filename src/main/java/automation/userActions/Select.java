package automation.userActions;

import automation.factory.Driver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;

import java.util.HashSet;

public class Select {

    private static Logger log = Logger.getLogger(Select.class);

    public static void selectDropDownByVisibleText(By locator, String text){
        Wait.waitForPageLoad();
        String firstCharacterDropDownVal = ""+text.charAt(0);
        HashSet<String> uniqueDropDownVals = new HashSet<>();
        try {
            org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(Driver.getDriver().findElement(locator));
            select.selectByVisibleText(text);
        }catch (ElementNotInteractableException e){
            org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(Driver.getDriver().findElement(locator));
            while(!select.getFirstSelectedOption().getText().equalsIgnoreCase(text) &&
                    uniqueDropDownVals.add(select.getFirstSelectedOption().getText())) {
                Driver.getDriver().findElement(locator).sendKeys(firstCharacterDropDownVal);
            }
        }
        log.info("Selected dropdown by visible text "+text);
    }
}
