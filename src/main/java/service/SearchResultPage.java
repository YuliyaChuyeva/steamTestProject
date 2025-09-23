package service;

import elementFactory.GameTitleLabel;
import elementFactory.Link;
import elementFactory.PageTitleElement;
import org.openqa.selenium.By;

public class SearchResultPage extends AbstractPage {
    private Link firstGameLink = new Link(By.cssSelector(".search_result_row:first-child"));
    private PageTitleElement pageTitleElement = new PageTitleElement();
    private GameTitleLabel firstGameTitleLabel = new GameTitleLabel(By.cssSelector(".search_result_row:first-child .title"));

    public SearchResultPage clickFirstGame() {
        firstGameLink.click();
        return this;
    }

    public String getFirstGameTitle() {
        return firstGameTitleLabel.getTitleText();
    }

    @Override
    public String getPageTitle() {
        return pageTitleElement.getTitle();
    }
}
