import core.Driver;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.object.Game;
import service.pages.GamePage;
import service.pages.MainPage;
import service.pages.SearchResultPage;
import service.pages.menu.HeaderMenuOption;
import service.pages.menu.StoreSubMenuOption;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class GameDetailsComparisonTest extends BaseTest {
    private static final String GAME_NAME = "No Man's Sky";

    @Test
    public void searchAndOpenGame() {
        String firstGameTitle = new MainPage()
                .searchGame(GAME_NAME)
                .getFirstGameTitle();
        Assert.assertEquals(firstGameTitle, GAME_NAME, "The first game in the search results doesn't match the expected one");
        String pageTitleAfterClick = new SearchResultPage()
                .clickFirstGame()
                .getPageTitle();
        Assert.assertTrue(pageTitleAfterClick.contains(GAME_NAME), "The wrong page is open.");
    }

    @Test
    public void verifyGameDataBetweenPages() {
        SearchResultPage searchResultPage = new MainPage()
                .searchGame(GAME_NAME);
        Game gameFromSearch = searchResultPage.getFirstGameData();
        searchResultPage.clickFirstGame();
        Game gameAfterSearch = new GamePage().getGameDetails();
        assertThat(gameFromSearch)
                .usingRecursiveComparison()
                .as("Game data mismatch between search results and game page")
                .isEqualTo(gameAfterSearch);
    }

    @Test
    public void shouldNavigateToWishlist() {
        new MainPage()
                .getHeaderMenu()
                .navigate(HeaderMenuOption.STORE, StoreSubMenuOption.WISHLIST);
        assertThat(Driver.getInstance().getCurrentUrl()).contains("/wishlist");
    }
}
