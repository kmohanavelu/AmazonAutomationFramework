package automation.factory;

import automation.decorators.DriverDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * This method initialises the browser instance for the browser factory
     * WebDriver Manager manages the driver setup needed for each of the driver.
     * Hence there is no need to download and set driver executable path manually for each of the WebDriver
     * **/
    public static void initialiseBrowser(String browserName){
        WebDriver webDriver = null;
        switch (browserName) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                webDriver = new SafariDriver();
                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                webDriver = new OperaDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                webDriver = new InternetExplorerDriver();
                break;
        }
        driver.set((DriverDecorator)webDriver);
    }
}
