package automation.pages;

import automation.factory.BrowserFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountAndLists {

    public AccountAndLists(){
        PageFactory.initElements(BrowserFactory.driver.get(),this);
        WebDriverWait wait = new WebDriverWait(BrowserFactory.driver.get(), Duration.ofSeconds(10));
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(helloSignIn));
    }

    @FindBy(id = "nav-link-accountList-nav-line-1")
    private WebElement helloSignIn;

    @FindBy(css = "#nav-link-accountList > .nav-line-2")
    private WebElement signInBtn;

    public void navigateToSignInPage(){
        Actions actions = new Actions(BrowserFactory.getDriver());
        actions.moveToElement(helloSignIn);
        actions.click(helloSignIn);
        signInBtn.click();
    }

}
