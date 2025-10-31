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
        WebDriver d = Driver.getInstance();
        d.manage().window().maximize();
        d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String url = PropertiesReader.getInstance().getUrl();
        d.get(url);
        d.manage().deleteAllCookies();
        Cookie lang = new Cookie.Builder("Steam_Language", "english")
                .domain("store.steampowered.com")
                .path("/")
                .isSecure(true)
                .build();
        d.manage().addCookie(lang);
        d.navigate().refresh();
        log.info("Go to the main page (English): {}", url);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
