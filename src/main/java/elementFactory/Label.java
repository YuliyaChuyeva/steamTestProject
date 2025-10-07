package elementFactory;

import org.openqa.selenium.By;

public class Label extends BaseElement {
    public Label(By locator) {
        super(locator);
    }

    @Override
    public String getText() {
        return super.getText().trim();
    }
}

