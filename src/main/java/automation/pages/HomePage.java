package automation.pages;


import automation.factory.BrowserFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(BrowserFactory.driver.get(),this);
        WebDriverWait wait = new WebDriverWait(BrowserFactory.driver.get(), Duration.ofSeconds(10));
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(userGreeting));
    }

    @FindBy(xpath = "//span[@id='nav-link-accountList-nav-line-1']")
    private WebElement userGreeting;


    public String getUserGreeting(){
        return userGreeting.getText();
    }
}
