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

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(BrowserFactory.driver.get(),this);
        WebDriverWait wait = new WebDriverWait(BrowserFactory.driver.get(), Duration.ofSeconds(10));
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
        emailIdTxtBox.sendKeys(userName);
    }

    public void enterPassword(String password){
        passwordTxtBox.sendKeys(password);
    }

    public void clickContinueBtn(){
        continueBtn.click();
    }

    public HomePage clickSubmitBtn(){
        signInSubmitBtn.click();
        return new HomePage();
    }




}
