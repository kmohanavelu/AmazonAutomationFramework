package automation.pages.components;

import automation.abstracts.AbstractComponent;
import automation.factory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Products extends AbstractComponent {

    public Products(){
        super(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOfElementLocated(sectionHeader));
    }

    By sectionHeader = By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']//span[text()='RESULTS']");
    By productDescription = By.xpath("//div[@data-component-type='s-search-result']//h2");
    By productRating = By.xpath("//div[@class='a-section a-spacing-none a-spacing-top-micro']//i//span");
    By numberOfCustomerReviews = By.xpath("//div[@class='a-section a-spacing-none a-spacing-top-micro']//a[contains(@href,'customerReview')]");
    By price = By.xpath("//div[contains(@class,'s-price-instructions-style')]//span[@class='a-price-whole']");

    public void searchForProduct(String productNameOrDescription){

    }

    public void getProductPrice(WebElement element){

    }

    public void getProductDescription(WebElement element){

    }

    public void getNumberOfCustomerReviews(WebElement element){

    }

    public void getProductRating(WebElement element){

    }

}

