package listener;

import core.driver.Driver;
import core.util.PropertiesReader;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.AllureAttachments;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        trySaveScreenshot(result);
        AllureAttachments.attachScreenshotToAllure();
    }

    private void trySaveScreenshot(ITestResult result) {
        try {
            WebDriver webDriver = Driver.getInstance();
            File screenshotSource = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            String screenshotsDirPath = PropertiesReader.getInstance().getScreenshotsDir();
            String screenshotFileName = String.format("%s_thread_%d.png",
                    result.getMethod().getMethodName(),
                    Thread.currentThread().getId());
            Path screenshotTargetPath = Path.of(screenshotsDirPath).resolve(screenshotFileName);
            Files.copy(screenshotSource.toPath(), screenshotTargetPath);
        } catch (IOException | WebDriverException e) {
            log.warn("Failed to save screenshot for test: {}", result.getMethod().getMethodName(), e);
        }
    }
}
