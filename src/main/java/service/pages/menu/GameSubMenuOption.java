package service.pages.menu;

import core.util.Lang;
import lombok.Getter;

@Getter
public enum GameSubMenuOption {
    TOP_SELLERS("gameSubMenu.top_sellers"),
    DISCOUNT_EVENTS("gameSubMenu.discount_events");

    private final String menuKey;

    GameSubMenuOption(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getDisplayName() {
        return Lang.get(menuKey);
    }
}
