package elementFactory;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

@Slf4j
public class SearchBox extends TextBox {
    public SearchBox(By locator) {
        super(locator);
    }

    public void search(String query) {
        log.info("Starting search for query: '{}' using element: {}", query, locator);
        sendText(query);
        log.info("Submitting search form element: {}", locator);
        getElement().submit();
        log.info("Search submitted for query: '{}'", query);
    }
}
