package automation.core;

import automation.businessComponents.Filter;
import automation.businessComponents.Login;
import automation.businessComponents.SearchForProduct;

public class User {

    private Login login;
    private Filter filter;
    private SearchForProduct searchForProduct;

    public Login getLogin(){
        if(login == null){
            login = new Login();
        }
        return login;
    }

    public Filter getFilter(){
        if(filter == null){
            filter = new Filter();
        }
        return filter;
    }

    public SearchForProduct getSearchForProduct(){
        if(searchForProduct == null){
            searchForProduct = new SearchForProduct();
        }
        return searchForProduct;
    }
}
