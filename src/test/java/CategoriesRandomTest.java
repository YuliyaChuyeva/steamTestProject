import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import service.pages.CategoriesSearchResultPage;
import service.pages.MainPage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CategoriesRandomTest extends BaseTest {

    @Test
    public void randomCategory() {
        String choiceCategory = new MainPage().getGameMenu().openCategoriesMenu().clickRandomCategory();
        List<Integer> mismatched = new CategoriesSearchResultPage().getMismatchedGames(choiceCategory, 5);
        assertThat(mismatched)
                .withFailMessage("Some cards do not contain a category tag '%s': %s", choiceCategory, mismatched)
                .isEmpty();
    }
}
