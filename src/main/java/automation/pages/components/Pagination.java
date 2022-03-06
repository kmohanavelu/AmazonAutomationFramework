package automation.pages.components;

import automation.abstracts.AbstractComponent;
import automation.factory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Pagination extends AbstractComponent {

    public Pagination(){
        super(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOfElementLocated(paginationStrip));
    }

    By paginationStrip = By.className("s-pagination-strip");
    By previous = By.xpath("//span[contains(@class,'s-pagination-previous') and contains(text(),'Previous')]");
    By next = By.xpath("//a[contains(@class,'s-pagination-next')]");
    By paginationButton = By.xpath("//a[contains(@class,'s-pagination-item s-pagination-button')]");
}
