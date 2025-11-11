package element_factory;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

@Slf4j
public class Link extends BaseElement {
    public Link(By locator) {
        super(locator);
    }

    public String getHref() {
        String href = getElement().getAttribute("href");
        log.info("Got href '{}' from element: {}", href, locator);
        return href;
    }
}
