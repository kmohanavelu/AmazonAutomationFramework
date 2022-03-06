package automation.tests.testCases;

import automation.core.BaseTest;
import automation.core.Page;
import automation.core.User;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validUserNameAndPassword(){
        User user = new User();
        user.getLogin().login("kishoremohanavelu@yahoo.com","HowAreYou4532#");
    }

}
