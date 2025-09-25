package service.pages;

import elementFactory.GamePriceLabel;
import elementFactory.GameTitleLabel;
import elementFactory.PageTitleElement;
import elementFactory.ReleaseDateLabel;
import org.openqa.selenium.By;
import service.object.Game;

public class GamePage extends AbstractPage {
    private GamePriceLabel priceElement = new GamePriceLabel(By.cssSelector(".game_purchase_price.price"));
    private ReleaseDateLabel releaseElement = new ReleaseDateLabel(By.cssSelector(".release_date .date"));
    private GameTitleLabel titleElement = new GameTitleLabel(By.id("appHubAppName"));
    private PageTitleElement pageTitleElement = new PageTitleElement();

    public Game getGameDetails() {
        String title = titleElement.getTitleText();
        String price = priceElement.getText();
        String releaseDate = releaseElement.getText();
        return new Game(title, price, releaseDate);
    }

    @Override
    public String getPageTitle() {
        return pageTitleElement.getTitle();
    }
}
