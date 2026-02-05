package core.util;

import lombok.extern.slf4j.Slf4j;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Slf4j
public final class Lang {
    private static ResourceBundle bundle;

    private Lang() {
    }

    private static ResourceBundle getBundle() {
        if (bundle == null) {
            String lang = System.getProperty("language");
            if (lang == null || lang.isBlank()) {
                lang = PropertiesReader.getInstance().getLanguage();
            }
            bundle = ResourceBundle.getBundle("languages." + lang.trim().toLowerCase());
        }
        return bundle;
    }

    public static String get(String key) {
        try {
            return getBundle().getString(key);
        } catch (MissingResourceException e) {
            log.error("Failed to resolve translation key '{}': key not found in bundle", key, e);
            throw e;
        }
    }
}
