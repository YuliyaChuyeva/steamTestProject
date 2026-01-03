package service.pages.menu;

import element_factory.Label;
import element_factory.SearchBox;
import org.openqa.selenium.By;
import service.pages.SearchResultPage;

public class GameMenu {

    private final String GAME_MENU_XPATH = "//div[@role='navigation']//div[contains(text(),'%s')]";
    private final String GAME_SUBMENU_XPATH = "//span[contains(text(),'%s')]";
    private final By SEARCH_INPUT = By.cssSelector("#store_nav_search_term, input[name='term']");
    private Label cartLink = new Label(By.cssSelector("a[href*='/cart']"));
    private final By CATEGORIES_MENU = By.xpath("//div[@role='navigation']//div[contains(text(),'Categories')]");
    private final Label FIRST_CATEGORY_NAME = new Label(By.xpath("//a[contains(@href,'/category/')]/descendant::div[normalize-space() and not(descendant::*)][1]"));

    private By getMenuItem(GameMenuOption menuOption) {
        return By.xpath(String.format(GAME_MENU_XPATH, menuOption.getDisplayName()));
    }

    private By getSubMenuItem(GameSubMenuOption subMenuOption) {
        return By.xpath(String.format(GAME_SUBMENU_XPATH, subMenuOption.getDisplayName()));
    }

    public void navigate(GameMenuOption menuOption, GameSubMenuOption subMenuOption) {
        new Label(getMenuItem(menuOption)).click();
        new Label(getSubMenuItem(subMenuOption)).click();
    }

    public void navigate(GameMenuOption menuOption) {
        new Label(getMenuItem(menuOption)).click();
    }

    public SearchResultPage searchGame(String query) {
        new SearchBox(SEARCH_INPUT).search(query);
        return new SearchResultPage();
    }

    public void openCart() {
        cartLink.click();
    }

    public CategoriesSubMenu openCategoriesMenu() {
        navigate(GameMenuOption.CATEGORIES);
        FIRST_CATEGORY_NAME.waitForVisibility();
        return new CategoriesSubMenu();
    }
}
