package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DriverFactory {
    public static WebDriver initDriver() {
        String browserName = PropertiesReader.getInstance().getBrowser();
        BrowserType browserType;
        try {
            browserType = BrowserType.valueOf(
                    browserName == null ? "CHROME" : browserName.toUpperCase().trim()
            );
        } catch (IllegalArgumentException e) {
            browserType = BrowserType.CHROME;
        }
        WebDriver driver;
        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                String downloadDir = PropertiesReader.getInstance().getDownloadDirPath();
                ChromeOptions options = buildChromeOptions(downloadDir);
                ChromeDriver chromeDriver = new ChromeDriver(options);
                allowDownloadsViaCDP(chromeDriver, downloadDir);
                driver = chromeDriver;
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

    private static ChromeOptions buildChromeOptions(String downloadDir) {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = buildChromePrefs(downloadDir);
        options.setExperimentalOption("prefs", prefs);
        addChromeArguments(options);
        return options;
    }

    private static Map<String, Object> buildChromePrefs(String downloadDir) {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("translate", Map.of("enabled", false));
        prefs.put("translate_whitelists", new HashMap<>());
        prefs.put("intl.accept_languages", "en-US,en");
        prefs.put("download.default_directory", downloadDir);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", false);
        prefs.put("safebrowsing_for_trusted_sources_enabled", true);
        return prefs;
    }

    private static void addChromeArguments(ChromeOptions options) {
        options.addArguments("--lang=en-US");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--safebrowsing-disable-download-protection");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu-sandbox");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-running-insecure-content");
    }

    private static void allowDownloadsViaCDP(ChromeDriver chromeDriver, String downloadDir) {
        Map<String, Object> params = new HashMap<>();
        params.put("behavior", "allow");
        params.put("downloadPath", downloadDir);
        chromeDriver.executeCdpCommand("Page.setDownloadBehavior", params);
        log.info("Allowed downloads via CDP to: {}", downloadDir);
    }
}
