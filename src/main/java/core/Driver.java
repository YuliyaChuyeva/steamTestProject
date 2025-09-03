package core;

import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Driver {
    private static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Driver.class);

    private Driver() {
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            driver = DriverFactory.initDriver();
        }
        return driver;
    }

    public static void navigateMainPage() {
        driver.manage().window().maximize();
        String url = PropertiesReader.getInstance().getUrl();
        driver.get(url);
        logger.info("Переход на главную страницу: {}", url);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
