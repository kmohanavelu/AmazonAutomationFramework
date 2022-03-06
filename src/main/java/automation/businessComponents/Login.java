package automation.businessComponents;

import automation.core.Page;
import automation.userActions.Verify;
import org.testng.Assert;

public class Login {

    public void enterUserName(String username){
        Page page = new Page();
        page.loginPage().enterUserName(username);
        page.loginPage().clickContinueBtn();
//        Assert.assertTrue(Verify.verifyIfElementPresent(page.loginPage().getSignInSubmitBtn()),
//                "Entering username failed. Navigation did not happen from Signin-Username page to SignIn-Password page");
    }

    public void enterPassword(String password){
        Page page = new Page();
        page.loginPage().enterPassword(password);
        page.loginPage().clickSubmitBtn();
//        Assert.assertTrue(Verify.verifyIfElementPresent(page.homePage().getUserGreeting()),
//                "SignIn failed. Navigation did not happend from SignPage to Home Page");
    }

    public void navigateToSignInPage(){
        Page page = new Page();
        page.accountAndListsSection().navigateToSignInPage();
//        Assert.assertTrue(Verify.verifyIfElementPresent(page.loginPage().getEmailIdTxtBox()),
//                "Navigate to SignPage failed");
    }

    public void login(String userName,String password){
        navigateToSignInPage();
        enterUserName(userName);
        enterPassword(password);
    }
}
