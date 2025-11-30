import core.DownloadHelper;
import core.Driver;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
@Slf4j
public abstract class BaseTest {
    @BeforeMethod
    public void setUp() {
        Driver.navigateMainPage();
        DownloadHelper.cleanDownloadDir();
    }


    @AfterMethod
    public void close() {
            Driver.getInstance().close();
        }
    }


