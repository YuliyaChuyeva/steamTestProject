package service.pages;

import elementFactory.*;
import org.openqa.selenium.By;
import service.object.Game;

public class SearchResultPage extends AbstractPage {
    private Link firstGameLink = new Link(By.cssSelector(".search_result_row:first-child"));
    private PageTitleElement pageTitleElement = new PageTitleElement();
    private GameTitleLabel firstGameTitleLabel = new GameTitleLabel(By.cssSelector(".search_result_row:first-child .title"));
    private GamePriceLabel firstGameprice = new GamePriceLabel(By.cssSelector(".search_result_row:first-child .discount_final_price"));
    private ReleaseDateLabel firstGameReleaseDate = new ReleaseDateLabel(By.cssSelector(".search_result_row:first-child .search_released"));

    public Game getFirstGameData() {
        String title = firstGameTitleLabel.getTitleText();
        String price = firstGameprice.getText();
        String releasedDate = firstGameReleaseDate.getText();
        return new Game(title, price, releasedDate);
    }

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
