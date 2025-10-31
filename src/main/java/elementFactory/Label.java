package elementFactory;

import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Label extends BaseElement {
    public Label(By locator) {
        super(locator);
    }

    @Override
    public String getText() {
        return super.getText().trim();
    }

    public void hover() {
        WebElement el = getVisibleElement();
        new Actions(Driver.getInstance()).moveToElement(el).perform();
    }
}

