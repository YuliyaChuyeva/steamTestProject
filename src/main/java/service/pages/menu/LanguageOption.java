package service.pages.menu;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum LanguageOption {
    ENGLISH("english", "en"),
    NORWEGIAN("norwegian", "no"),
    GERMAN("german", "de"),
    FRENCH("french", "fr"),
    ITALIAN("italian", "it"),
    DUTCH("dutch", "nl"),
    JAPANESE("japanese", "ja"),
    THAI("thai", "th"),
    BULGARIAN("bulgarian", "bg"),
    CZECH("czech", "cs"),
    DANISH("danish", "da"),
    SPANISH("spanish", "es"),
    POLISH("polish", "pl"),
    RUSSIAN("russian", "ru");

    private final String hrefKey;
    private final String htmlLang;

    LanguageOption(String hrefKey, String htmlLang) {
        this.hrefKey = hrefKey;
        this.htmlLang = htmlLang;
    }

    public static LanguageOption fromHtmlLang(String htmlLang) {
        return Arrays.stream(values())
                .filter(l -> l.htmlLang.equalsIgnoreCase(htmlLang))
                .findFirst()
                .orElse(null);
    }
}
