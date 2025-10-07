package service.pages;

import elementFactory.Label;
import org.openqa.selenium.By;
import service.object.Game;

public class GamePage extends AbstractPage {
    private Label priceElement = new Label(By.xpath("//div[@id='game_area_purchase_section_add_to_cart_97032']//div[@class='discount_final_price']"));
    private Label releaseElement = new Label(By.cssSelector(".release_date .date"));
    private Label titleElement = new Label(By.id("appHubAppName"));

    public Game getGameDetails() {
        String title = titleElement.getText();
        String price = priceElement.getText();
        String releaseDate = releaseElement.getText();
        return new Game(title, price, releaseDate);
    }
}
