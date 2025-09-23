package elementFactory;


import core.Driver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageTitleElement {
    public String getTitle() {
        String title = Driver.getInstance().getTitle();
        return title;
    }
}
