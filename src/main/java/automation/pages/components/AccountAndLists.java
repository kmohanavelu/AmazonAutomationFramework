package automation.pages.components;

import automation.factory.Driver;
import automation.utitilites.UserAction;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountAndLists {

    private static Logger log = Logger.getLogger(AccountAndLists.class);

    public AccountAndLists(){
        PageFactory.initElements(Driver.getDriver(),this);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(helloSignIn));
    }

    @FindBy(id = "nav-link-accountList-nav-line-1")
    private WebElement helloSignIn;

    @FindBy(css = "#nav-link-accountList > .nav-line-2")
    private WebElement signInBtn;

    public void navigateToSignInPage(){
        UserAction.moveToElement(helloSignIn);
        UserAction.click(signInBtn);
    }

}
