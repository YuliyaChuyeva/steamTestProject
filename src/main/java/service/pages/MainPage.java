package service.pages;

import io.qameta.allure.Step;
import service.pages.menu.GameMenu;

public class MainPage extends AbstractPage {

    public SearchResultPage searchGame(String query) {
        GameMenu gameMenu = getGameMenu();
        return gameMenu.searchGame(query);
    }
    @Step("Change language")
    public void changeLanguage() {
        getHeaderMenu().changeLanguage();
    }
}
