package service.pages.menu;

import core.util.Lang;
import lombok.Getter;

@Getter
public enum StoreSubMenuOption {
    HOME("store.submenu.home"),
    DISCOVERY_QUEUE("store.submenu.discovery_queue"),
    WISHLIST("store.submenu.wishlist"),
    POINTS_SHOP("store.submenu.points_shop"),
    NEWS("store.submenu.news"),
    STATS("store.submenu.stats");

    private final String menuKey;

    StoreSubMenuOption(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getDisplayName() {
        return Lang.get(menuKey);
    }
}
