package element_factory;

import core.Driver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
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

    public static List<Label> findAll(By locator) {
        String raw = locator.toString();
        if (!raw.startsWith("By.xpath: ")) {
            throw new IllegalArgumentException(
                    "Label.findAll() supports only XPath locators. Provided: " + raw
            );
        }
        List<WebElement> elements = findElements(locator);
        if (elements.isEmpty()) {
            log.warn("No elements found by locator: {}", locator);
            return List.of();
        }
        String xpath = raw.substring("By.xpath: ".length()).trim();
        return IntStream.rangeClosed(1, elements.size())
                .mapToObj(i -> new Label(By.xpath("(" + xpath + ")[" + i + "]")))
                .toList();
    }

    public static int scrollToBottomUntilNoNewElements(String xpathOfElements) {
        return BaseElement.scrollToBottomUntilNoNewElements(xpathOfElements);
    }
}

