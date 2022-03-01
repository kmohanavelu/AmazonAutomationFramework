package automation.pages;

public class User {

    public static HomePage homePage(){
        return new HomePage();
    }

    public static LoginPage loginPage(){
        return new LoginPage();
    }

    public static AccountAndLists getAccountAndListsSection(){
        return new AccountAndLists();
    }
}
