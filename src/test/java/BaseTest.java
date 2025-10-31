import core.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    @BeforeMethod
    public void setUp() {
        Driver.navigateMainPage();
    }


    @AfterMethod
    public void close() {
            Driver.getInstance().close();
        }
    }


