import core.Driver;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import service.pages.InstallSteamPage;
import service.pages.MainPage;

import java.io.File;

@Slf4j
public class DownloadSteamTest extends BaseTest {
    @Test
    public void installSteam() {
        new MainPage().getHeaderMenu().openInstallSteam();
        File installer = new InstallSteamPage().downloadInstaller();
        String name = installer.getName().toLowerCase();
        Assert.assertTrue(
                name.endsWith(".exe")
                        || name.endsWith(".msi")
                        || name.endsWith(".dmg")
                        || name.endsWith(".deb")
                        || name.endsWith(".pkg"),
                "Unexpected installer extension: " + name
        );
    }

    @AfterMethod(alwaysRun = true)
    @Override
    public void close() {
        Driver.quitDriver();
    }
}
