package service.pages.menu;

import core.PropertiesReader;
import core.Waiter;
import element_factory.Button;
import element_factory.Label;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

@Slf4j
public class HeaderMenu {
    private static final String MAIN_MENU_XPATH = "//*[@id='global_header']//a[@data-tooltip-content='.submenu_%s']";
    private static final String MAIN_MENU_XPATH_ABOUT_SUPPORT =
            "//*[@id='global_header']//a[contains(@class,'menuitem') and contains(text(),'%1$s')]";
    private static final String SUB_MENU_ITEM_XPATH = "//div[contains(@class,'supernav_content')]//a[normalize-space()='%s']";
    private static final Button installSteam = new Button(By.xpath("//div[@class='header_installsteam_btn_content']"));
    private static final Label htmlTag = new Label("//html");
    private static final Label languagePullDown = new Label(By.id("language_pulldown"));
    private static final String LANGUAGE_OPTION_XPATH = "//a[contains(@href, 'l=%s')]";

    public void changeLanguage() {
        String actualLang = htmlTag.getAttribute("lang").trim().toLowerCase();
        String expectedLangCode = PropertiesReader.getInstance().getLanguage();
        LanguageOption expectedLang = LanguageOption.fromHtmlLang(expectedLangCode);
        if (actualLang.equalsIgnoreCase(expectedLang.getHtmlLang())) {
            log.info("Language already OK: {}", actualLang);
            return;
        }
        log.info("Changing language from '{}' to '{}'", actualLang, expectedLang.getHtmlLang());
        languagePullDown.click();
        new Label(String.format(LANGUAGE_OPTION_XPATH, expectedLang.getHrefKey())).click();
        Waiter.waitForPageToLoad();
        Waiter.waitForAjax();
        log.info("Language changed successfully ");
    }

    public void hover(HeaderMenuOption main) {
        new Label(getMenuByText(main.getMenuId())).hover();
    }

    public void openInstallSteam() {
        installSteam.click();
    }

    public void navigate(HeaderMenuOption main, StoreSubMenuOption sub) {
        hover(main);
        new Label(getSubMenuItemLocator(sub.getDisplayName())).click();
    }

    public void navigate(HeaderMenuOption main) {
        new Label(getMenuByAboutSupport(main.getDisplayName())).click();
    }

    private By getMenuByAboutSupport(String name) {
        return By.xpath(String.format(MAIN_MENU_XPATH_ABOUT_SUPPORT, name));
    }

    private By getMenuByText(String text) {
        return By.xpath(String.format(MAIN_MENU_XPATH, text));
    }

    private By getSubMenuItemLocator(String name) {
        return By.xpath(String.format(SUB_MENU_ITEM_XPATH, name));
    }
}


