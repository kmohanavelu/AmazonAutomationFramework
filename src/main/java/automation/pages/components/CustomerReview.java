package automation.pages.components;

import automation.abstracts.AbstractComponent;
import automation.core.UserAction;
import automation.factory.Driver;
import automation.userActions.Scroll;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class CustomerReview extends AbstractComponent {

    public CustomerReview(){
        super(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOfElementLocated(sectionHeader));
    }

    By sectionHeader = By.xpath("//div[@id='reviewsRefinements']//span[contains(text(),'Customer Review')]");

    By fourRating = By.xpath("//div[@id='reviewsRefinements']//a[.//span[text()='4 Stars & Up']]");

    By threeRating = By.xpath("//div[@id='reviewsRefinements']//a[.//span[text()='3 Stars & Up']]");

    By twoRating = By.xpath("//div[@id='reviewsRefinements']//a[.//span[text()='2 Stars & Up']]");

    By oneRating = By.xpath("//div[@id='reviewsRefinements']//a[.//span[text()='1 Star & Up']]");

    public void select4StarRating(){
        Scroll.scrollElementIntoView(fourRating);
        UserAction.click.clickElement(fourRating);
    }

    public void select3StarRating(){
        Scroll.scrollElementIntoView(threeRating);
        UserAction.click.clickElement(threeRating);
    }

    public void select2StarRating(){
        Scroll.scrollElementIntoView(twoRating);
        UserAction.click.clickElement(twoRating);
    }

    public void select1StarRating(){
        Scroll.scrollElementIntoView(oneRating);
        UserAction.click.clickElement(oneRating);
    }
}
