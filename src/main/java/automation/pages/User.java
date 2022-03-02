package automation.pages;

import automation.pages.components.AccountAndLists;
import automation.pages.components.CustomerReview;
import automation.pages.components.SearchBox;

public class User {

    public static HomePage homePage;
    public static LoginPage loginPage;
    public static AccountAndLists accountAndLists;
    public static SearchBox searchBox;
    public static CustomerReview customerReview;

    public static HomePage homePage(){
        if(homePage == null){
            homePage = new HomePage();
        }
        return homePage;
    }

    public static LoginPage loginPage(){
        if(loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public static AccountAndLists accountAndListsSection(){
        if(accountAndLists == null){
            accountAndLists = new AccountAndLists();
        }
        return accountAndLists;
    }

    public static SearchBox searchBoxComponent() {
        if(searchBox == null){
            searchBox = new SearchBox();
        }
        return searchBox;
    }

    public static CustomerReview customerReview(){
        if(customerReview == null){
            customerReview = new CustomerReview();
        }
        return customerReview;
    }
}
