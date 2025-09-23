import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.MainPage;
import service.SearchResultPage;

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
}
