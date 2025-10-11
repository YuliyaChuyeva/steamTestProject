package service.pages.menu;

import elementFactory.Link;
import org.openqa.selenium.By;

public class StoreSubMenu {
    private By getSubMenuItemLocator(String name) {
        return By.xpath(String.format(
                "//div[contains(@class,'supernav_content')]//a[normalize-space()='%s']",
                name));
    }

    public void click(StoreSubMenuOption option) {
        new Link(getSubMenuItemLocator(option.getDisplayName())).click();
    }
}
