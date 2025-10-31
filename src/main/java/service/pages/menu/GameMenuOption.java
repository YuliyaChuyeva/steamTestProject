package service.pages.menu;


import lombok.Getter;

@Getter
public enum GameMenuOption {
    BROWSE("Browse"),
    RECOMMENDATIONS("Recommendations"),
    CATEGORIES("Categories"),
    WAYS_TO_PLAY("Ways to Play"),
    SPECIAL_SECTIONS("Special Sections");
    private final String displayName;

    GameMenuOption(String displayName) {
        this.displayName = displayName;
    }
}
