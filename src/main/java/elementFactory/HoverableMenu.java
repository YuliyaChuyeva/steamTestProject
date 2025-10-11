package elementFactory;

import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HoverableMenu extends BaseElement {
    public HoverableMenu(By locator) {
        super(locator);
    }

    public void hover() {
        WebElement el = getVisibleElement();
        new Actions(Driver.getInstance()).moveToElement(el).perform();
    }
}
