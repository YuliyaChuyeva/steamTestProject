package service.pages;

import core.util.DownloadHelper;
import element_factory.Button;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.io.File;

@Slf4j
public class InstallSteamPage {
    private final Button bigInstallBtn = new Button(By.xpath("//a[contains(@class,'about_install_steam_link')]"));
    public static final long STEAM_INSTALLER_MIN_BYTES = 2_306_867L;

    public File downloadInstaller() {
        bigInstallBtn.scrollToElement();
        bigInstallBtn.click();
        log.info("Clicked big 'Install Steam' — waiting...");
        return DownloadHelper.waitForDownload(
                "steam", STEAM_INSTALLER_MIN_BYTES);
    }
}