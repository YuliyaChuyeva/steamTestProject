import core.Driver;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.MainPage;
import service.SearchResultPage;

@Slf4j
public class SeleniumTest {
    private MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        Driver.navigateMainPage();
        mainPage = new MainPage();
    }

    @Test
    public void searchAndOpenGame() {
        SearchResultPage resultPage = mainPage.searchGame("No Man's Sky");
        String firstGameTitle = resultPage.getFirstGameTitle();
        log.info("First game in results: {}", firstGameTitle);
        Assert.assertEquals(firstGameTitle, "No Man's Sky", "Первая игра не соответствует");
        resultPage.clickFirstGame();
        String title = Driver.getInstance().getTitle();
        Assert.assertTrue(title.contains("No Man's Sky"), "The wrong page is open.");
        System.out.println("Game page successfully opened.");
    }

    @AfterMethod
    public void closeDriver() {
        Driver.quitDriver();
    }
}
