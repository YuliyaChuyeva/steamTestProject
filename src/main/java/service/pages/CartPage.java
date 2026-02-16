package service.pages;

import core.util.Lang;
import element_factory.Button;
import element_factory.Label;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import service.ui_object.Game;

@Slf4j
public class CartPage extends AbstractPage {
    private final Label cartTitle = new Label(By.xpath("//div[@class='Panel Focusable']//div[@id='«r1»']"));
    private final Label cartPrice = new Label(By.xpath("//div[@class='Panel Focusable']//div[starts-with(@class,'pk-')]"));
    private Button removeItem = new Button(By.xpath(String.format("//div[@role='button' and normalize-space()='%s']", Lang.get("cart.remove"))));
    private Button removeAllItems = new Button(By.xpath(String.format("//div[@role='button'and normalize-space()='%s']", Lang.get("cart.removeAll"))));
    private final Label emptyCartText = new Label(By.xpath(String.format("//*[contains(.,'%s') or contains(.,'Your Shopping Cart is empty') or contains(.,'There are no items in your cart')]", Lang.get("cart.empty"))));

    @Step("Read game from cart (title, price)")
    public Game getCartGame() {
        String title = cartTitle.getText();
        String price = cartPrice.getText();
        String releaseDate = "";
        log.info("Cart contains game: title='{}', price='{}'", title, price);
        return new Game(title, price, releaseDate);
    }

    @Step("Remove item from cart")
    public void removeItem() {
        removeItem.click();
        log.info("Clicked 'remove' button");
    }

    @Step("Remove all items from cart")
    public void removeAllItems() {
        removeAllItems.click();
        log.info("Clicked 'remove all items' button");
    }

    @Step("Check cart is empty")
    public boolean isEmpty() {
        try {
            emptyCartText.waitForVisibility();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
