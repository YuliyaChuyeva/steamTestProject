package service.pages.menu;

import elementFactory.Label;
import org.openqa.selenium.By;

public class HeaderMenu {
    private static final String MAIN_MENU_XPATH = "//*[@id='global_header']//a[@data-tooltip-content='.submenu_%s']";
    private static final String MAIN_MENU_XPATH_ABOUT_SUPPORT =
            "//*[@id='global_header']//a[contains(@class,'menuitem') and contains(text(),'%1$s')]";

    public void hover(HeaderMenuOption main) {
        new Label(getMenuByText(main.getDisplayName())).hover();
    }

    public void navigate(HeaderMenuOption main, StoreSubMenuOption sub) {
        hover(main);
        new Label(getSubMenuItemLocator(sub.getDisplayName())).click();
    }

    public void navigate(HeaderMenuOption main) {
        new Label(menuByAboutSupport(main.getDisplayName())).click();
    }

    private By menuByAboutSupport(String name) {
        return By.xpath(String.format(MAIN_MENU_XPATH_ABOUT_SUPPORT, name));
    }

    private By getMenuByText(String text) {
        return By.xpath(String.format(MAIN_MENU_XPATH, text));
    }

    private By getSubMenuItemLocator(String name) {
        return By.xpath(String.format(
                "//div[contains(@class,'supernav_content')]//a[normalize-space()='%s']",
                name));
    }
}


