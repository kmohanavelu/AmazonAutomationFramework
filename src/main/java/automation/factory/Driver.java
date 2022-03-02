package automation.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * This method initialises the browser instance for the browser factory
     * WebDriver Manager manages the driver setup needed for each of the driver.
     * Hence there is no need to download and set driver executable path manually for each of the WebDriver
     * **/
    public static void initialiseBrowser(Browser browser){
        WebDriver webDriver = null;
        switch (browser) {
            case Firefox:
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            case Edge:
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            case Chrome:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            case Safari:
                WebDriverManager.safaridriver().setup();
                webDriver = new SafariDriver();
                break;
            case Opera:
                WebDriverManager.operadriver().setup();
                webDriver = new OperaDriver();
                break;
            case InternetExplorer:
                WebDriverManager.iedriver().setup();
                webDriver = new InternetExplorerDriver();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + browser.name());
        }
        webDriver.get("https://www.amazon.in/");
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();
        driver.set(webDriver);
    }

    public static WebDriver getDriver(){
        return driver.get();
    }
}
