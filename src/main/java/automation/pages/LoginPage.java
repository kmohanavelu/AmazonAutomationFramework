package automation.pages;

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

public class LoginPage {

    private static Logger log = Logger.getLogger(LoginPage.class);

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(signInHeader));
    }

    @FindBy(xpath = "//h1[contains(text(),'Sign-In')]")
    private WebElement signInHeader;

    @FindBy(id = "ap_email")
    private WebElement emailIdTxtBox;

    @FindBy(id = "ap_password")
    private WebElement passwordTxtBox;

    @FindBy(css = ".a-button-inner > #continue")
    private WebElement continueBtn;

    @FindBy(id = "signInSubmit")
    private WebElement signInSubmitBtn;

    public void enterUserName(String userName){
        UserAction.sendKeys(emailIdTxtBox,userName);
    }

    public void enterPassword(String password){
        UserAction.sendKeys(passwordTxtBox,password);
    }

    public void clickContinueBtn(){
        UserAction.click(continueBtn);
    }

    public HomePage clickSubmitBtn(){
        UserAction.click(signInSubmitBtn);
        return new HomePage();
    }
}
