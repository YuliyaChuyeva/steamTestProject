import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.object.Game;
import service.pages.CartPage;
import service.pages.GamePage;
import service.pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CartTest extends BaseTest {
    private static final String GAME_NAME = "No Man's Sky";

    @Test
    public void addGameToCart_showConfirmation() {
        new MainPage().getGameMenu().searchGame(GAME_NAME).clickFirstGame();
        GamePage gamePage = new GamePage();
        Assert.assertTrue(gamePage.getPageTitle().contains(GAME_NAME));
        gamePage.addToCart();
        Assert.assertTrue(gamePage.isAddedToCartMessageShown(), "Confirmation 'Added to your cart' not shown");
        gamePage.closeAddToCartModalIfVisible();
    }

    @Test
    public void removeGameToCart() {
        new MainPage().getGameMenu().searchGame(GAME_NAME).clickFirstGame();
        new GamePage().addToCart().closeAddToCartModalIfVisible();
        new MainPage().getGameMenu().openCart();
        CartPage cart = new CartPage();
        cart.removeItem();
        Assert.assertTrue(cart.isEmpty(), "The cart was not emptied after removing the game.");
    }

    @Test
    public void cartAddedGameCorrectDetails() {
        new MainPage().getGameMenu().searchGame(GAME_NAME).clickFirstGame();
        GamePage gamePage = new GamePage();
        Game expectedGame = gamePage.getGameDetails();
        gamePage.addToCart().viewMyCart();
        CartPage cartPage = new CartPage();
        Game actualGame = cartPage.getCartGame();
        expectedGame = new Game(expectedGame.getTitle(),
                expectedGame.getPrice().replace(" USD", "").trim(),
                expectedGame.getReleaseDate());
        assertThat(actualGame)
                .usingRecursiveComparison()
                .ignoringFields("releaseDate") // в корзине нет даты
                .as("The game in cart doesn't match the one added")
                .isEqualTo(expectedGame);
    }
}
