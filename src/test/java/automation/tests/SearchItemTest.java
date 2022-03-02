package automation.tests;

import automation.core.BaseTest;
import automation.pages.User;
import org.testng.annotations.Test;

public class SearchItemTest extends BaseTest {

    @Test
    public void searchItem() throws Exception{
        new LoginTest().validUserNameAndPassword();
        User.searchBoxComponent().selectSearchCategory("Sports, Fitness & Outdoors");
        User.searchBoxComponent().searchForItem("badminton rackets for men");
        User.customerReview().select4StarRating();
        Thread.sleep(10000);
    }
}
