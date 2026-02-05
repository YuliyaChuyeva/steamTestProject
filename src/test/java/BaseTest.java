import core.driver.Driver;
import core.util.DownloadHelper;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import service.pages.menu.HeaderMenu;

@Slf4j
@Listeners(core.TestListener.class)
public abstract class BaseTest {
    @BeforeMethod
    public void setUp() {
        Driver.navigateMainPage();
        new HeaderMenu().changeLanguage();
        DownloadHelper.cleanDownloadDir();
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        Driver.quitDriver();
    }
}


