package service.pages.menu;

import lombok.Getter;

@Getter
public enum HeaderMenuOption {
    STORE("Store"),
    COMMUNITY("Community"),
    ABOUT("About"),
    SUPPORT("SUPPORT");

    private final String displayName;

    HeaderMenuOption(String displayName) {
        this.displayName = displayName;
    }
}
