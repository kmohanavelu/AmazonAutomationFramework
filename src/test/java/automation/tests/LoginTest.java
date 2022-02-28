package automation.tests;

import automation.core.BaseTest;
import automation.pages.AccountAndLists;
import automation.pages.HomePage;
import automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validUserNameAndPassword(){
        AccountAndLists page = new AccountAndLists();
        page.navigateToSignInPage();
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserName("kishoremohanavelu@yahoo.com");
        loginPage.clickContinueBtn();
        loginPage.enterPassword("HowAreYou4532#");
        HomePage homePage = loginPage.clickSubmitBtn();
        Assert.assertTrue(homePage.getUserGreeting().contains("kishore"));
    }

}
