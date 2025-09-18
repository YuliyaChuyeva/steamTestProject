package service;

import elementFactory.SearchBox;
import org.openqa.selenium.By;

public class MainPage {
    private SearchBox searchBox= new SearchBox(By.xpath("//input[@id='store_nav_search_term']"));

    public SearchResultPage searchGame(String query){
        searchBox.search(query);
        return new SearchResultPage();
    }

}
