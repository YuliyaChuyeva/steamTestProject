import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.object.Game;
import service.pages.GamePage;
import service.pages.MainPage;
import service.pages.SearchResultPage;

@Slf4j
public class SeleniumTest extends BaseTest {
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
        GamePage gamePage = new GamePage();
        Game gameAfterSearch = gamePage.getGameDetails();
        Assert.assertEquals(gameFromSearch, gameAfterSearch, "Game data mismatch between search results and game page");
        //assertThat(gameFromSearch).isEqualTo(gameAfterSearch);
    }
}
