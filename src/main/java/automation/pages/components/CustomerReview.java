package automation.pages.components;

import automation.factory.Driver;
import automation.pages.User;
import automation.utitilites.UserAction;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static automation.constants.Environment.FIND_ELEMENT_WAIT_TIME;

public class CustomerReview {

    public CustomerReview(){
        PageFactory.initElements(Driver.getDriver(),this);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(FIND_ELEMENT_WAIT_TIME));
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(sectionHeader));
    }

    @FindBy(xpath = "//div[@id='reviewsRefinements']//span[contains(text(),'Customer Review')]")
    private WebElement sectionHeader;

    @FindBy(xpath = "//div[@id='reviewsRefinements']//a[.//span[text()='4 Stars & Up']]")
    private WebElement fourRating;

    @FindBy(xpath = "//div[@id='reviewsRefinements']//a[.//span[text()='3 Stars & Up']]")
    private WebElement threeRating;

    @FindBy(xpath = "//div[@id='reviewsRefinements']//a[.//span[text()='2 Stars & Up']]")
    private WebElement twoRating;

    @FindBy(xpath = "//div[@id='reviewsRefinements']//a[.//span[text()='1 Star & Up']]")
    private WebElement oneRating;

    public void select4StarRating(){
        UserAction.click(fourRating);
    }

    public void select3StarRating(){
        UserAction.click(threeRating);
    }

    public void select2StarRating(){
        UserAction.click(twoRating);
    }

    public void select1StarRating(){
        UserAction.click(oneRating);
    }
}
