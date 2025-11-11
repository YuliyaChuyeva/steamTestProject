package element_factory;

import core.Driver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

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
        var driver = Driver.getInstance();
        var elements = driver.findElements(locator);
        List<Label> labels = new ArrayList<>(elements.size());
        String locatorText = locator.toString();
        if (locatorText.startsWith("By.xpath: ")) {
            String xp = locatorText.substring("By.xpath: ".length()).trim();
            for (int i = 1; i <= elements.size(); i++) {
                labels.add(new Label(By.xpath("(" + xp + ")[" + i + "]")));
            }
            return labels;
        }
        for (int i = 0; i < elements.size(); i++) {
            labels.add(new Label(locator));
        }
        return labels;
    }
}

