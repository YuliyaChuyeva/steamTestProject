package service.pages;

import elementFactory.Button;
import elementFactory.Label;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import service.object.Game;

@Slf4j
public class CartPage extends AbstractPage {
    private final Label cartTitle = new Label(By.xpath("//div[@class='Panel Focusable']//div[@id='«r1»']"));
    private final Label cartPrice = new Label(By.xpath("//div[@class='Panel Focusable']//div[starts-with(@class,'pk-')]"));
    private Button removeItem = new Button(By.xpath("//div[@role='button' and normalize-space()='Remove']"));
    private Button removeAllItems = new Button(By.xpath("//div[@role='button'and normalize-space()='Remove all items']"));
    private final Label emptyCartText = new Label(
            By.xpath("//*[contains(.,'Your cart is empty') or contains(.,'Your Shopping Cart is empty') or contains(.,'There are no items in your cart')]")
    );

    public Game getCartGame() {
        String title = cartTitle.getText();
        String price = cartPrice.getText();
        String releaseDate = "";
        log.info("Cart contains game: title='{}', price='{}'", title, price);
        return new Game(title, price, releaseDate);
    }

    public void removeItem() {
        removeItem.click();
        log.info("Clicked 'remove' button");
    }

    public void removeAllItems() {
        removeAllItems.click();
        log.info("Clicked 'remove all items' button");
    }

    public boolean isEmpty() {
        try {
            emptyCartText.waitForVisibility();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
