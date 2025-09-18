package elementFactory;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

@Slf4j
public class TextBox extends BaseElement {
    public TextBox(By locator) {
        super(locator);
    }

    public void clear() {
        log.info("Clearing text in element:{}", locator);
        getElement().clear();
        log.info("Cleared text in element: {}", locator);
    }

    public void sendText(String text) {
        log.info("Sending text '{}' to element:{}", text, locator);
        getElement().clear();
        getElement().sendKeys(text);
        log.info("Sent text '{}' to element: {}", text, locator);
    }

    public String getValue() {
        String value = getElement().getAttribute("value");
        log.info("Got value '{}' from element: {}", value, locator);
        return value;
    }
}
