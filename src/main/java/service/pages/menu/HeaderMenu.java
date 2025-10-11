package service.pages.menu;

import elementFactory.HoverableMenu;
import org.openqa.selenium.By;

public class HeaderMenu {
    private final StoreSubMenu storeSubMenu = new StoreSubMenu();
    private static final String MAIN_MENU_XPATH = "//*[@id='global_header']//a[@data-tooltip-content='.submenu_%s']";

    private By menuByText(String text) {
        return By.xpath(String.format(MAIN_MENU_XPATH, text));
    }

    public void hover(HeaderMenuOption main) {
        new HoverableMenu(menuByText(main.getDisplayName())).hover();
    }

    public void navigate(HeaderMenuOption main, StoreSubMenuOption sub) {
        hover(main);
        storeSubMenu.click(sub);
    }
}
