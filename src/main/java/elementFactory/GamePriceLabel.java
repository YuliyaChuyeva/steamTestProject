package elementFactory;

import org.openqa.selenium.By;

public class GamePriceLabel extends BaseElement {
    public GamePriceLabel(By locator) {
        super(locator);
    }

    @Override
    public String getText() {
        return super.getText().trim();
    }
}
