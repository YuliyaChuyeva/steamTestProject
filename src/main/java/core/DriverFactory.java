package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {
    public static WebDriver initDriver() {
        String browserName = PropertiesReader.getInstance().getBrowser();
        BrowserType browserType;
        if (browserName == null) {
            browserType = BrowserType.CHROME;
        } else {
            try {
                browserType = BrowserType.valueOf(browserName.toUpperCase().trim());
            } catch (IllegalArgumentException e) {
                browserType = BrowserType.CHROME;
            }
        }
        WebDriver driver;
        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        return driver;
    }
}
