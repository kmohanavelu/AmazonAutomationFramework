package automation.tests.testCases;

import automation.core.BaseTest;
import automation.core.Page;
import automation.core.User;
import org.testng.annotations.Test;

public class SearchItemTest extends BaseTest {

    @Test(invocationCount = 1,threadPoolSize = 1)
    public void searchItem() throws Exception{
        User user = new User();
        user.getLogin().login("kishoremohanavelu@yahoo.com","HowAreYou4532#");
        user.getSearchForProduct().searchProduct("Sports, Fitness & Outdoors","bicycle handle bar riser");
        user.getFilter().applyCustomerReviewFilter("3");
        user.getFilter().applyDiscountFilter("35");
        Thread.sleep(10000);
    }
}
