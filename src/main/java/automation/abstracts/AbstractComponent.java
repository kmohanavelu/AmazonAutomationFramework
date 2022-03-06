package automation.abstracts;

import automation.constants.Environment;
import automation.factory.Driver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    protected WebDriverWait wait;

    public AbstractComponent(WebDriver driver){
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Environment.FIND_ELEMENT_WAIT_TIME));
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        PageFactory.initElements(driver, this);
    }
}
