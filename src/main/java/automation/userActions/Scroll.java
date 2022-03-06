package automation.userActions;

import automation.factory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import javax.xml.crypto.Data;

public class Scroll {

    public static void scrollElementIntoView(By locator){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", Driver.getDriver().findElement(locator));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
