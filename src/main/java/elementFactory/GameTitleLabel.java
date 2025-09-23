package elementFactory;

import org.openqa.selenium.By;

public class GameTitleLabel extends BaseElement {
    public GameTitleLabel(By locator) {
        super(locator);
    }

    public String getTitleText() {
        return getText();
    }
}
