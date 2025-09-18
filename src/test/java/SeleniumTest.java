import core.Driver;
import elementFactory.Link;
import elementFactory.SearchBox;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SeleniumTest {
    private SearchBox searchBox;
    private Link firstGameLink;

    @BeforeMethod
    public void setUp() {
        Driver.navigateMainPage();
        searchBox = new SearchBox(By.xpath("//input[@id='store_nav_search_term']"));
        firstGameLink = new Link(By.cssSelector(".search_result_row:first-child"));
    }

    @Test
    public void searchAndOpenGame() {
        searchBox.search("No Man's Sky");
        firstGameLink.click();
        String title = Driver.getInstance().getTitle();
        Assert.assertTrue(title.contains("No Man's Sky"), "The wrong page is open.");
        System.out.println("Game page successfully opened.");
    }

    @AfterMethod
    public void closeDriver() {
        Driver.quitDriver();
    }
}
