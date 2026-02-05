package core.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {
    public static void waitForPageToLoad() {
        new WebDriverWait(Driver.getInstance(), Duration.ofSeconds(20))
                .until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }

    public static void waitForAjax() {
        new WebDriverWait(Driver.getInstance(), Duration.ofSeconds(10))
                .until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return window.jQuery != undefined && jQuery.active ===0;"));
    }
}