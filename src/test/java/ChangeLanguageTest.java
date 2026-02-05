import core.util.PropertiesReader;
import element_factory.Label;
import org.testng.annotations.Test;
import service.pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeLanguageTest extends BaseTest {
    private final Label htmlTag = new Label("//html");

    @Test
    public void testLanguage() {
        new MainPage().changeLanguage();
        String actualLang = htmlTag.getAttribute("lang").trim().toLowerCase();
        String expectedLang = PropertiesReader.getInstance().getLanguage();
        assertThat(actualLang)
                .as("Language  not change correctly!")
                .isEqualTo(expectedLang);
    }
}
