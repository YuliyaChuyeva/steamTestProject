package service.pages;

import service.pages.menu.GameMenu;

public class MainPage extends AbstractPage {

    public SearchResultPage searchGame(String query) {
        GameMenu gameMenu = getGameMenu();
        return gameMenu.searchGame(query);
    }
    public void changeLanguage() {
        getHeaderMenu().changeLanguage();
    }
}
