package core;

import lombok.experimental.UtilityClass;

import java.util.Locale;

@UtilityClass
public class StringUtil {
    public static String normalizeText(String s) {
        if (s == null) return "";
        return s.toLowerCase(Locale.ROOT)
                .replace('-', ' ')
                .replace('–', ' ')
                .replace('\n', ' ')
                .replaceAll("\\s+", " ")
                .trim();
    }
}
