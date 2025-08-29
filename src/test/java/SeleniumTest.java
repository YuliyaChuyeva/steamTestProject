import core.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class SeleniumTest {

    @Test
    public void testSteamRun() {
        WebDriver driver = DriverSingleton.getDriver();
        driver.manage().window().maximize();
        driver.get("https://store.steampowered.com/");
        driver.findElement(By.xpath("//*[contains(text(),'Установить')]")).click();
        driver.findElement(By.xpath("//a[@class='smooth_scroll']")).click();
        driver.findElement(By.xpath("//div[@class='cta_btn']//a[contains(text(), 'Каталог')]")).click();
        driver.findElement(By.xpath("//div[@class='gutter_items']//a[contains(text(), 'Лидеры продаж')]")).click();
        driver.findElement(By.xpath("//div[@class='col search_name ellipsis']//span[contains(text(), 'No Man')]")).click();
        driver.findElement(By.xpath("//a[@id='btn_add_to_cart_97032']")).click();
    }
}
