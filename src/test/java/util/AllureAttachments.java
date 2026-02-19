package util;

import core.driver.Driver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class AllureAttachments {
    private AllureAttachments() {
    }

    public static void attachScreenshotToAllure() {
        WebDriver driver = Driver.getInstance();
        if (driver == null) return;
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        attach(screenshot);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] attach(byte[] screenshot) {
        return screenshot;
    }
}
