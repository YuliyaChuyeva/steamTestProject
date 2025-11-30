package service.pages;

import core.Driver;
import service.pages.menu.GameMenu;
import service.pages.menu.HeaderMenu;

public abstract class AbstractPage {
    private HeaderMenu headerMenu = new HeaderMenu();
    private GameMenu gameMenu = new GameMenu();

    public String getPageTitle() {
        return Driver.getInstance().getTitle();
    }

    public HeaderMenu getHeaderMenu() {
        return headerMenu;
    }

    public GameMenu getGameMenu() {
        return gameMenu;
    }
}
