package core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
        return properties.getProperty("browser").trim();
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getUri() {
        return properties.getProperty("uri");
    }

    public String getSteamApiKey() {
        return readFileName("steam_api_key.txt");
    }

    public String getSteamId() {
        return readFileName("steam_id64.txt");
    }

    private String readFileName(String fileName) {
        try {
            String value = Files.readString(Path.of(fileName)).trim();
            if (value.isEmpty()) {
                throw new RuntimeException(fileName + " is empty");
            }
            return value;
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to read from file: " + fileName +
                            ". File must exist and must NOT be committed to git.",
                    e
            );
        }
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
