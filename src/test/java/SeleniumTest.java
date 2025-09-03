import core.PropertiesReader;
import core.Driver;
import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class SeleniumTest {

    @Test
    public void testSteamRun() {
        WebDriver driver = DriverFactory.initDriver();
        driver.get(PropertiesReader.getInstance().getUrl());
        driver.findElement(By.xpath("//*[contains(text(),'Установить')]")).click();
        driver.findElement(By.xpath("//a[@class='smooth_scroll']")).click();
        driver.findElement(By.xpath("//div[@class='cta_btn']//a[contains(text(), 'Каталог')]")).click();
        driver.findElement(By.xpath("//div[@class='gutter_items']//a[contains(text(), 'Лидеры продаж')]")).click();
        driver.findElement(By.xpath("//div[@class='col search_name ellipsis']//span[contains(text(), 'No Man')]")).click();
        driver.findElement(By.xpath("//a[@id='btn_add_to_cart_97032']")).click();
    }

    @Test
    public void searchAndOpenGame() {
        WebDriver driver = Driver.getInstance();
        Driver.navigateMainPage();
        WebElement searchElement = driver.findElement(By.xpath("//input[@id='store_nav_search_term']"));
        searchElement.sendKeys("No Man's Sky");
        searchElement.submit();
        WebElement firstResult = driver.findElement(By.xpath("//a[contains(@href, '/app/')] [1]"));
        firstResult.click();
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("No Man's Sky"), "The wrong page is open.");
        System.out.println("Game page successfully opened.");
    }

    @AfterMethod
    public void closeDriver() {
        Driver.quitDriver();
    }
}
