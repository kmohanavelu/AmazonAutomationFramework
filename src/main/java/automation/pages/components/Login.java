package automation.pages.components;

import automation.abstracts.AbstractComponent;
import automation.core.UserAction;
import automation.factory.Driver;
import automation.pages.HomePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class Login extends AbstractComponent {

    public Login(){
        super(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOfElementLocated(signInHeader));
    }

    By signInHeader = By.xpath("//h1[contains(text(),'Sign-In')]");
    By emailIdTxtBox = By.id("ap_email");
    By passwordTxtBox = By.id("ap_password");
    By continueBtn = By.xpath("//span[@id='continue']//input[@id='continue']");
    By signInSubmitBtn = By.id("signInSubmit");

    public void enterUserName(String userName){
        UserAction.type.sendKeys(emailIdTxtBox,userName);
    }

    public void enterPassword(String password){
        UserAction.type.sendKeys(passwordTxtBox,password);
    }

    public void clickContinueBtn(){
        UserAction.click.btnClickWithDissappearCheck(continueBtn);
    }

    public HomePage clickSubmitBtn(){
        UserAction.click.btnClickWithDissappearCheck(signInSubmitBtn);
        return new HomePage();
    }


}
