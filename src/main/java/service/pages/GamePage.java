package service.pages;

import elementFactory.Button;
import elementFactory.Label;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import service.object.Game;

@Slf4j
public class GamePage extends AbstractPage {
    private Label priceElement = new Label(By.xpath("//div[@id='game_area_purchase_section_add_to_cart_97032']//div[@class='discount_final_price']"));
    private Label releaseElement = new Label(By.cssSelector(".release_date .date"));
    private Label titleElement = new Label(By.id("appHubAppName"));
    private Button addtoCartBtn = new Button(By.xpath("//a[contains(@id,'btn_add_to_cart_')]//span[text()='Add to Cart']"));
    private Label addedToCartConfirmation = new Label(By.xpath("//div[@role='dialog']//div[contains(text(),'Added to your cart')]"));
    private Button viewMyCart = new Button(By.xpath("//div[@role='dialog']//button[contains(@class,'DialogButton') and contains(@class,'Primary')]"));

    public Game getGameDetails() {
        String title = titleElement.getText();
        String price = priceElement.getText();
        String releaseDate = releaseElement.getText();
        return new Game(title, price, releaseDate);
    }

    public GamePage addToCart() {
        addtoCartBtn.scrollToElement();
        addtoCartBtn.click();
        log.info("Clicked 'Add to Cart' button");
        return this;
    }

    public GamePage viewMyCart() {
        viewMyCart.click();
        return this;
    }

    public boolean isAddedToCartMessageShown() {
        return addedToCartConfirmation.getText().contains("Added to your cart");
    }

    public GamePage closeAddToCartModalIfVisible() {
        try {
            Button closeModal = new Button(By.xpath("//div[@class='closeButton']"));
            closeModal.click();
            log.info("Closed Add to Cart modal");
        } catch (Exception ignored) {
            log.info("No modal to close");
        }
        return this;
    }
}
