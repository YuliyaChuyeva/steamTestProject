package core;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DownloadHelper {
    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }

    public static void prepareDownloadDir() {
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        String dir = propertiesReader.getDownloadDirPath();
        propertiesReader.ensureDirExists(dir);
        if (propertiesReader.isCleanDownloadsOnStart()) {
            propertiesReader.cleanDir(dir);
            log.info("Cleaned downloads dir: {}", dir);
        }
    }

    public static void allowDownloadsViaCDP(WebDriver driver) {
        String dir = PropertiesReader.getInstance().getDownloadDirPath();
        if (driver instanceof ChromeDriver chromeDriver) {
            Map<String, Object> params = new HashMap<>();
            params.put("behavior", "allow");
            params.put("downloadPath", dir);
            chromeDriver.executeCdpCommand("Page.setDownloadBehavior", params);
            log.info("Allowed downloads via CDP to: {}", dir);
        }
    }

    public static File waitForAnyDownload(String mask, Duration timeout) {
        String dirPath = PropertiesReader.getInstance().getDownloadDirPath();
        Instant end = Instant.now().plus(timeout);
        String needle = mask.toLowerCase();
        while (Instant.now().isBefore(end)) {
            File[] files = new File(dirPath).listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().toLowerCase().contains(needle)) {
                        log.info("Download started: {}", file.getName());
                        return file;
                    }
                }
            }
            sleep(300);
        }
        return null;
    }

    public static File waitForCompletedDownload(String mask, Duration timeout) {
        String dirPath = PropertiesReader.getInstance().getDownloadDirPath();
        Instant end = Instant.now().plus(timeout);
        String needle = mask.toLowerCase();
        while (Instant.now().isBefore(end)) {
            File[] files = new File(dirPath).listFiles();
            if (files != null) {
                for (File file : files) {
                    String n = file.getName().toLowerCase();
                    if (n.contains(needle) && !n.endsWith(".crdownload") && file.length() > 0) {
                        log.info("Download completed: {}", file.getName());
                        return file;
                    }
                }
            }
            sleep(500);
        }
        return null;
    }

    public static File waitForDownloadTwoPhase(String mask,
                                               Duration startTimeout,
                                               Duration finishTimeout) {
        File started = waitForAnyDownload(mask, startTimeout);
        if (started == null) {
            throw new AssertionError("Download did not start within " + startTimeout.getSeconds() + "s");
        }
        File done = waitForCompletedDownload(mask, finishTimeout);
        if (done == null) {
            throw new AssertionError("Download did not finish within " + finishTimeout.getSeconds() + "s");
        }
        return done;
    }
}

