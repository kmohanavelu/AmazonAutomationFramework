package automation.pages.components;

import automation.factory.Driver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static automation.constants.Environment.FIND_ELEMENT_WAIT_TIME;

public class Brand {

    public Brand(){
        PageFactory.initElements(Driver.getDriver(),this);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(FIND_ELEMENT_WAIT_TIME));
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
//        wait.until(ExpectedConditions.visibilityOf(sectionHeader));
    }

}
