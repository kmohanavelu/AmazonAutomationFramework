package automation.pages.components;

import automation.abstracts.AbstractComponent;
import automation.core.UserAction;
import automation.factory.Driver;
import automation.userActions.Scroll;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class Discount extends AbstractComponent {

    public Discount() {
        super(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOfElementLocated(sectionHeader));
    }

    By sectionHeader = By.id("p_n_pct-off-with-tax-title");
    By tenPercentOff = By.xpath("//ul[@aria-labelledby='p_n_pct-off-with-tax-title']//span[text()='10% Off or more']");
    By twentyFivePercentOff = By.xpath("//ul[@aria-labelledby='p_n_pct-off-with-tax-title']//span[text()='25% Off or more']");
    By thirtyFivePercentOff = By.xpath("//ul[@aria-labelledby='p_n_pct-off-with-tax-title']//span[text()='35% Off or more']");
    By fiftyPercentOff = By.xpath("//ul[@aria-labelledby='p_n_pct-off-with-tax-title']//span[text()='50% Off or more']");
    By sixtyPercentOff = By.xpath("//ul[@aria-labelledby='p_n_pct-off-with-tax-title']//span[text()='60% Off or more']");
    By seventyPercentOff = By.xpath("//ul[@aria-labelledby='p_n_pct-off-with-tax-title']//span[text()='70% Off or more']");

    public void applyTenPercentDiscount(){
        Scroll.scrollElementIntoView(tenPercentOff);
        UserAction.click.clickElement(tenPercentOff);
    }

    public void applyTwentyFivePercentDiscount(){
        Scroll.scrollElementIntoView(twentyFivePercentOff);
        UserAction.click.clickElement(twentyFivePercentOff);
    }

    public void applyThirtyFivePercentDiscount(){
        Scroll.scrollElementIntoView(thirtyFivePercentOff);
        UserAction.click.clickElement(thirtyFivePercentOff);
    }

    public void applyFiftyPercentDiscount(){
        Scroll.scrollElementIntoView(fiftyPercentOff);
        UserAction.click.clickElement(fiftyPercentOff);
    }

    public void applySixtyPercentDiscount(){
        Scroll.scrollElementIntoView(sixtyPercentOff);
        UserAction.click.clickElement(sixtyPercentOff);
    }

    public void applySeventyPercentDiscount(){
        Scroll.scrollElementIntoView(seventyPercentOff);
        UserAction.click.clickElement(seventyPercentOff);
    }
}
