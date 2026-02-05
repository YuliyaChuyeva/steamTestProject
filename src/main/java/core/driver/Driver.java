package core.driver;

import core.util.PropertiesReader;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.time.Duration;


@Slf4j
public class Driver {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private Driver() {
    }

    public static WebDriver getInstance() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            driver = DriverFactory.initDriver();
            DRIVER.set(driver);
            log.info("Driver created for thread: {}", Thread.currentThread().getId());
        }
        return driver;
    }

    public static void navigateMainPage() {
        WebDriver webDriver = getInstance();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = PropertiesReader.getInstance().getUrl();
        webDriver.get(url);
        //applyEnglishCookie(webDriver);
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
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            try {
                driver.quit();
                log.info("Driver quit for thread: {}", Thread.currentThread().getId());
            } finally {
                DRIVER.remove();
            }
        }
    }
}
