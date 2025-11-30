package service.pages.menu;

import element_factory.Button;
import element_factory.Label;
import org.openqa.selenium.By;

public class HeaderMenu {
    private static final String MAIN_MENU_XPATH = "//*[@id='global_header']//a[@data-tooltip-content='.submenu_%s']";
    private static final String MAIN_MENU_XPATH_ABOUT_SUPPORT =
            "//*[@id='global_header']//a[contains(@class,'menuitem') and contains(text(),'%1$s')]";
    private static final String SUB_MENU_ITEM_XPATH = "//div[contains(@class,'supernav_content')]//a[normalize-space()='%s']";
    private static Button installSteam = new Button(By.xpath("//div[@class='header_installsteam_btn_content']"));

    private By getMenuByAboutSupport(String name) {
        return By.xpath(String.format(MAIN_MENU_XPATH_ABOUT_SUPPORT, name));
    }

    private By getMenuByText(String text) {
        return By.xpath(String.format(MAIN_MENU_XPATH, text));
    }

    private By getSubMenuItemLocator(String name) {
        return By.xpath(String.format(SUB_MENU_ITEM_XPATH, name));
    }

    public void hover(HeaderMenuOption main) {
        new Label(getMenuByText(main.getDisplayName())).hover();
    }

    public void openInstallSteam() {
        installSteam.click();
    }

    public void navigate(HeaderMenuOption main, StoreSubMenuOption sub) {
        hover(main);
        new Label(getSubMenuItemLocator(sub.getDisplayName())).click();
    }

    public void navigate(HeaderMenuOption main) {
        new Label(getMenuByAboutSupport(main.getDisplayName())).click();
    }
}


