package automation.businessComponents;

import automation.core.Page;

public class SearchForProduct {

    public void searchProduct(String searchCategory,String searchItem){
        Page page = new Page();
        page.searchBoxComponent().selectSearchCategory(searchCategory);
        page.searchBoxComponent().searchForItem(searchItem);

    }
}
