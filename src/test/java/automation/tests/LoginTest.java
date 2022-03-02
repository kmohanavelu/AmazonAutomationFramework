package automation.tests;

import automation.core.BaseTest;
import automation.pages.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validUserNameAndPassword(){
        User.accountAndListsSection().navigateToSignInPage();
        User.loginPage().enterUserName("kishoremohanavelu@yahoo.com");
        User.loginPage().clickContinueBtn();
        User.loginPage().enterPassword("HowAreYou4532#");
        User.loginPage().clickSubmitBtn();
        Assert.assertTrue(User.homePage().getUserGreeting().contains("kishore"));
    }

}
