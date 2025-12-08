package service.pages.menu;

import core.Lang;
import lombok.Getter;

@Getter
public enum HeaderMenuOption {
    STORE("header.store", "Store"),
    COMMUNITY("header.community", "Community"),
    ABOUT("header.about", "about"),
    SUPPORT("header.support", "support");

    private final String menuKey;
    private final String menuId;

    HeaderMenuOption(String menuKey, String menuId) {
        this.menuKey = menuKey;
        this.menuId = menuId;
    }

    public String getDisplayName() {
        return Lang.get(menuKey);
    }
}
