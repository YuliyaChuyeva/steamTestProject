package elementFactory;

import org.openqa.selenium.By;

public class ReleaseDateLabel extends BaseElement {
    public ReleaseDateLabel(By locator) {
        super(locator);
    }

    @Override
    public String getText() {
        return super.getText().trim();
    }
}
