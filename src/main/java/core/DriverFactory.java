package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;


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
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("translate", Map.of("enabled", false));
                prefs.put("translate_whitelists", new HashMap<>());
                prefs.put("intl.accept_languages", "en-US,en"); // заголовок Accept-Language
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--lang=en-US");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                driver = new ChromeDriver(options);
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
