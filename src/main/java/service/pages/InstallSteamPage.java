package service.pages;

import core.DownloadHelper;
import core.Driver;
import element_factory.Button;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.io.File;
import java.time.Duration;

@Slf4j
public class InstallSteamPage {
    private final Button bigInstallBtn = new Button(By.xpath("//a[contains(@class,'about_install_steam_link')]"));

    public File downloadInstaller() {
        DownloadHelper.prepareDownloadDir();
        DownloadHelper.allowDownloadsViaCDP(Driver.getInstance());
        bigInstallBtn.scrollToElement();
        bigInstallBtn.click();
        log.info("Clicked big 'Install Steam' — waiting...");
        return DownloadHelper.waitForDownloadTwoPhase(
                "steam",
                Duration.ofSeconds(8),
                Duration.ofSeconds(60)
        );
    }
}


