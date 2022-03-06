package automation.pages.components;

import automation.abstracts.AbstractComponent;
import automation.core.UserAction;
import automation.factory.Driver;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class AccountAndLists extends AbstractComponent {

    private static Logger log = Logger.getLogger(AccountAndLists.class);

    public AccountAndLists(){
        super(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOfElementLocated(helloSignIn));
    }

    By helloSignIn = By.xpath("//a[@id='nav-link-accountList']");

    By signInButton = By.xpath("//a[@class='nav-action-button' and @data-nav-ref='nav_signin']");

    public void navigateToSignInPage(){
        UserAction.move.moveToElement(helloSignIn);
        UserAction.click.clickBtn(signInButton);
    }
}
