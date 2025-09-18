package core;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


@Slf4j
public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            driver = DriverFactory.initDriver();
        }
        return driver;
    }

    public static void navigateMainPage() {
        Driver.getInstance().manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String url = PropertiesReader.getInstance().getUrl();
        driver.get(url);
        log.info("Go to the main page: {}", url);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
