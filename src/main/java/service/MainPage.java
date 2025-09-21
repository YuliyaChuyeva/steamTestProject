package service;

import elementFactory.PageTitleElement;
import elementFactory.SearchBox;
import org.openqa.selenium.By;

public class MainPage extends AbstractPage {
    private SearchBox searchInputGame = new SearchBox(By.id("store_nav_search_term"));
    private PageTitleElement pageTitleElement = new PageTitleElement();

    public SearchResultPage searchGame(String query) {
        searchInputGame.search(query);
        return new SearchResultPage();
    }

    @Override
    public String getPageTitle() {
        return pageTitleElement.getTitle();
    }
}
