package automation.core;

import automation.pages.HomePage;
import automation.pages.components.*;

public class Page {

    private HomePage homePage;
    private Login login;
    private AccountAndLists accountAndLists;
    private Discount discount;
    private SearchBox searchBox;
    private CustomerReview customerReview;

    public HomePage homePage(){
        if(homePage == null){
            homePage = new HomePage();
        }
        return homePage;
    }

    public Login loginPage(){
        if(login == null){
            login = new Login();
        }
        return login;
    }

    public AccountAndLists accountAndListsSection(){
        if(accountAndLists == null){
            accountAndLists = new AccountAndLists();
        }
        return accountAndLists;
    }

    public SearchBox searchBoxComponent() {
        if(searchBox == null){
            searchBox = new SearchBox();
        }
        return searchBox;
    }

    public CustomerReview customerReview(){
        if(customerReview == null){
            customerReview = new CustomerReview();
        }
        return customerReview;
    }

    public Discount discount(){
        if(discount == null){
            discount = new Discount();
        }
        return discount;
    }
}
