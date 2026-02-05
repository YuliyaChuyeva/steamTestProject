package core.util;

import core.driver.Driver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Arrays;

@Slf4j
public class DownloadHelper {
    public static String getDownloadDir() {
        return PropertiesReader.getInstance().getDownloadDirPath();
    }

    public static void cleanDownloadDir() {
        String dir = getDownloadDir();
        PropertiesReader props = PropertiesReader.getInstance();
        props.ensureDirExists(dir);
        props.cleanDir(dir);
        log.info("Cleaned downloads dir: {}", dir);
    }

    public static boolean isFileDownloaded(String namePart, long minBytes) {
        return Arrays.stream(getDownloadFiles())
                .anyMatch(file -> matchesDownloadedFile(file, namePart, minBytes));
    }

    public static File waitForDownload(String namePart, long minBytes) {
        new WebDriverWait(
                Driver.getInstance(),
                PropertiesReader.getInstance().getDownloadTimeout()
        ).until(webDriver -> isFileDownloaded(namePart, minBytes));
        File result = Arrays.stream(getDownloadFiles())
                .filter(file -> matchesDownloadedFile(file, namePart, minBytes))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("Downloaded file not found after wait")
                );
        log.info("Download completed: {} ({} bytes)", result.getName(), result.length());
        return result;
    }

    private static File[] getDownloadFiles() {
        File dir = new File(getDownloadDir());
        File[] files = dir.listFiles();
        return files != null ? files : new File[0];
    }

    private static boolean matchesDownloadedFile(File file, String namePart, long minBytes) {
        String fileName = file.getName().toLowerCase();
        String needle = namePart.toLowerCase();
        return fileName.contains(needle)
                && !fileName.endsWith(".crdownload")
                && file.length() >= minBytes;
    }
}