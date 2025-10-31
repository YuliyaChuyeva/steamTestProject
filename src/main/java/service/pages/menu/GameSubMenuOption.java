package service.pages.menu;

import lombok.Getter;

@Getter
public enum GameSubMenuOption {
    TOP_SELLERS("Top Sellers"),
    NEW_RELEASES("New Releases"),
    YOUR_WISHLIST("Your Wishlist");

    private final String displayName;

    GameSubMenuOption(String displayName) {
        this.displayName = displayName;
    }
}
