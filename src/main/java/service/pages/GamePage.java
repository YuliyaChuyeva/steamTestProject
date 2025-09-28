package service.pages;

import elementFactory.Label;
import elementFactory.PageTitleElement;
import org.openqa.selenium.By;
import service.object.Game;

public class GamePage extends AbstractPage {
    private Label priceElement = new Label(By.cssSelector(".game_purchase_price.price"));
    private Label releaseElement = new Label(By.cssSelector(".release_date .date"));
    private Label titleElement = new Label(By.id("appHubAppName"));
    private PageTitleElement pageTitleElement = new PageTitleElement();

    public Game getGameDetails() {
        String title = titleElement.getText();
        String price = priceElement.getText();
        String releaseDate = releaseElement.getText();
        return new Game(title, price, releaseDate);
    }

    @Override
    public String getPageTitle() {
        return pageTitleElement.getTitle();
    }
}
