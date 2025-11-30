package core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class PropertiesReader {
    private static PropertiesReader instance;
    private Properties properties;

    private PropertiesReader() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("Error, file config.properties not found in resources.");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error loading file config.properties");
        }
    }

    public static PropertiesReader getInstance() {
        if (instance == null) {
            instance = new PropertiesReader();
        }
        return instance;
    }

    public String getLanguage() {
        return properties.getProperty("language", "en").trim().toLowerCase();
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getDownloadDirPath() {
        String fromConfig = properties.getProperty("download.dir", "target/downloads");
        File dir = new File(fromConfig);
        if (!dir.isAbsolute()) {
            dir = new File(System.getProperty("user.dir"), fromConfig);
        }
        return dir.getAbsolutePath();
    }

    public Duration getDownloadTimeout() {
        String value = properties.getProperty("download.timeout.seconds", "300");
        long seconds = Long.parseLong(value.trim());
        return Duration.ofSeconds(seconds);
    }

    public void ensureDirExists(String dirPath) {
        new File(dirPath).mkdirs();
    }

    public void cleanDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) return;
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            try {
                file.delete();
            } catch (Exception ignored) {
            }
        }
    }
}
