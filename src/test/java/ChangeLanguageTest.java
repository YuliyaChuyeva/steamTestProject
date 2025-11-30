import core.PropertiesReader;
import element_factory.Label;
import org.testng.annotations.Test;
import service.pages.MainPage;

import static org.testng.Assert.assertEquals;

public class ChangeLanguageTest extends BaseTest {
    private static final Label htmlTag = new Label("//html");

    @Test
    public void testLanguage() {
        new MainPage().changeLanguage();
        String actualLang = htmlTag.getAttribute("lang").trim().toLowerCase();
        String expectedLang = PropertiesReader.getInstance().getLanguage();
        assertEquals(actualLang, expectedLang, "Language  not change correctly!");
    }
}
