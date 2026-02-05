package service.pages.menu;


import core.util.Lang;
import lombok.Getter;

@Getter
public enum GameMenuOption {
    BROWSE("gameMenu.browse"),
    RECOMMENDATIONS("gameMenu.recommendations"),
    CATEGORIES("gameMenu.categories"),
    WAYS_TO_PLAY("gameMenu.ways_to_play"),
    SPECIAL_SECTIONS("gameMenu.special_sections");

    private final String menuKey;

    GameMenuOption(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getDisplayName() {
        return Lang.get(menuKey);
    }
}
