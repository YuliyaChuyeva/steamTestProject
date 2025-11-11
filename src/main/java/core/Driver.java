package core;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Cookie;
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
        WebDriver webDriver = Driver.getInstance();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String url = PropertiesReader.getInstance().getUrl();
        webDriver.get(url);
        applyEnglishCookie(webDriver);
    }

    public static void applyEnglishCookie(WebDriver driver) {
        driver.manage().deleteAllCookies();
        Cookie lang = new Cookie.Builder("Steam_Language", "english")
                .domain("store.steampowered.com")
                .path("/")
                .isSecure(true)
                .build();
        driver.manage().addCookie(lang);
        driver.navigate().refresh();
        log.info("Applied language=english cookie and refreshed page");
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
