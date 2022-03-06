package automation.pages.components;

import automation.abstracts.AbstractComponent;
import automation.factory.Driver;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Getter
public class Brand extends AbstractComponent {

    public Brand(){
        super(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOf(Driver.getDriver().findElement(sectionHeader)));
    }

    By sectionHeader = By.xpath("//div[@id='brandsRefinements']//span[contains(text(),'Brand')]");
    By brandChoice = By.xpath("//div[@id='brandsRefinements']//li");

    public void selectBrandChoice(String choiceOfBrand){
        List<WebElement> brandChoices = Driver.getDriver().findElements(brandChoice);
        for (WebElement choice : brandChoices) {

        }
    }

}
