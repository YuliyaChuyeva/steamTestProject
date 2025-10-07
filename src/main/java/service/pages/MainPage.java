package service.pages;

import elementFactory.SearchBox;
import org.openqa.selenium.By;

public class MainPage extends AbstractPage {
    private SearchBox searchInputGame = new SearchBox(By.name("term"));

    public SearchResultPage searchGame(String query) {
        searchInputGame.search(query);
        return new SearchResultPage();
    }
}
