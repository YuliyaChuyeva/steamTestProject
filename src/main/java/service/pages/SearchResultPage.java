package service.pages;

import elementFactory.*;
import org.openqa.selenium.By;
import service.object.Game;

public class SearchResultPage extends AbstractPage {
    private Link firstGameLink = new Link(By.cssSelector(".search_result_row:first-child"));
    private PageTitleElement pageTitleElement = new PageTitleElement();
    private Label firstGameTitleLabel = new Label(By.cssSelector(".search_result_row:first-child .title"));
    private Label firstGameprice = new Label(By.cssSelector(".search_result_row:first-child .discount_final_price"));
    private Label firstGameReleaseDate = new Label(By.cssSelector(".search_result_row:first-child .search_released"));

    public Game getFirstGameData() {
        String title = firstGameTitleLabel.getText();
        String price = firstGameprice.getText();
        String releasedDate = firstGameReleaseDate.getText();
        return new Game(title, price, releasedDate);
    }

    public SearchResultPage clickFirstGame() {
        firstGameLink.click();
        return this;
    }

    public String getFirstGameTitle() {
        return firstGameTitleLabel.getText();
    }

    @Override
    public String getPageTitle() {
        return pageTitleElement.getTitle();
    }
}
