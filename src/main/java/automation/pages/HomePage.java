package automation.pages;

import automation.factory.Driver;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private static Logger log = Logger.getLogger(HomePage.class);

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(userGreeting));
    }

    @FindBy(xpath = "//span[@id='nav-link-accountList-nav-line-1']")
    private WebElement userGreeting;


    public String getUserGreeting(){
        return userGreeting.getText();
    }
}
