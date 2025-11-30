package service.pages.menu;

import lombok.Getter;

@Getter
public enum StoreSubMenuOption {
    HOME("Home"),
    DISCOVERY_QUEUE("Discovery queue"),
    WISHLIST("Wishlist"),
    POINTS_SHOP("Points shop"),
    NEWS("News"),
    STATS("Stats");

    private final String displayName;

    StoreSubMenuOption(String displayName) {
        this.displayName = displayName;
    }
}
