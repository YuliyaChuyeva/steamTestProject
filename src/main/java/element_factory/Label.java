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

    public Label(String xpath) {
        super(By.xpath(xpath));
    }

    @Override
    public String getText() {
        return super.getText().trim();
    }

    public void hover() {
        WebElement el = getVisibleElement();
        new Actions(Driver.getInstance()).moveToElement(el).perform();
    }

    public List<Label> findAll() {
        String xpath = getXPath();
        List<WebElement> elements = findElements();
        if (elements.isEmpty()) {
            log.warn("No elements found by xpath: {}", xpath);
            return List.of();
        }
        return IntStream.rangeClosed(1, elements.size())
                .mapToObj(i -> new Label("(" + xpath + ")[" + i + "]"))
                .toList();
    }

    //Находит дочерние элементы по ЛОКАТОРУ Label
    public List<Label> findAll(Label relativeLocator) {
        String rootXpath = this.getXPath();
        String childXpath = relativeLocator.getXPath();
        String relative = childXpath.startsWith(".")
                ? childXpath.substring(1)
                : "//" + childXpath.substring(2);
        String combined = "(" + rootXpath + ")" + relative;
        return new Label(combined).findAll();
    }
}

