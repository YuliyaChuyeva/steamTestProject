package service.pages;

import elementFactory.SearchBox;
import org.openqa.selenium.By;
import service.pages.menu.HeaderMenu;

public class MainPage extends AbstractPage {
    private SearchBox searchInputGame = new SearchBox(By.name("term"));
    private HeaderMenu headerMenu = new HeaderMenu();

    public SearchResultPage searchGame(String query) {
        searchInputGame.search(query);
        return new SearchResultPage();
    }

    public HeaderMenu getHeaderMenu() {
        return headerMenu;
    }
}
