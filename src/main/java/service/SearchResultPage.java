package service;

import elementFactory.Link;
import org.openqa.selenium.By;

public class SearchResultPage {
    private Link firstGameLink = new Link(By.cssSelector(".search_result_row:first-child"));

    public void clickFirstGame() {
        firstGameLink.click();
    }

    public String getFirstGameTitle() {
        return firstGameLink.getElement()
                .findElement(By.cssSelector(".title"))
                .getText();
    }
}
