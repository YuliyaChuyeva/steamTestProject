package core;

import java.util.ResourceBundle;

public final class Lang {
    private static ResourceBundle bundle;

    private Lang() {
    }

    private static ResourceBundle getBundle() {
        if (bundle == null) {
            String lang = PropertiesReader.getInstance().getLanguage();
            bundle = ResourceBundle.getBundle("languages." + lang);
        }
        return bundle;
    }

    public static String get(String key) {
        try {
            return getBundle().getString(key);
        } catch (Exception e) {
            return "??" + key + "??";
        }
    }
}
