package service.pages.menu;

import elementFactory.Label;
import elementFactory.SearchBox;
import org.openqa.selenium.By;
import service.pages.SearchResultPage;

public class GameMenu {
    private static final String GAME_MENU_XPATH = "//div[@role='navigation' and @aria-label='Store menu']//div[contains(text(),'%s')]";
    private static final String GAME_SUBMENU_XPATH = "//span[contains(text(),'%s')]";
    private static final By SEARCH_INPUT = By.cssSelector("#store_nav_search_term, input[name='term']");
    private final Label cartLink = new Label(By.cssSelector("a[href*='/cart']"));

    public void navigate(GameMenuOption menuOption, GameSubMenuOption subMenuOption) {
        new Label(getMenuItem(menuOption)).click();
        new Label(getSubMenuItem(subMenuOption)).click();
    }

    public SearchResultPage searchGame(String query) {
        new SearchBox(SEARCH_INPUT).search(query);
        return new SearchResultPage();
    }

    public void openCart() {
        cartLink.click();
    }

    private By getMenuItem(GameMenuOption menuOption) {
        return By.xpath(String.format(GAME_MENU_XPATH, menuOption.getDisplayName()));
    }

    private By getSubMenuItem(GameSubMenuOption subMenuOption) {
        return By.xpath(String.format(GAME_SUBMENU_XPATH, subMenuOption.getDisplayName()));
    }
}
