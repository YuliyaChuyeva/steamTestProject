import core.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import service.object.GameCard;
import service.pages.CategoriesSearchResultPage;
import service.pages.MainPage;

import java.util.List;

@Slf4j
public class CategoriesRandomTest extends BaseTest {
    @Test
    public void randomCategory() {
        String chosenCategory = new MainPage()
                .getGameMenu()
                .openCategoriesMenu()
                .clickRandomCategory();
        String expectedTag = StringUtil.normalizeText(chosenCategory);
        List<GameCard> cards = new CategoriesSearchResultPage().getGameCardsOnPage();
        SoftAssertions softly = new SoftAssertions();
        cards.forEach(card ->
                softly.assertThat(card.getTags())
                        .as("Game: %s | Expected tag: %s | Tags: %s",
                                card.getTitle(), expectedTag, card.getTags())
                        .anyMatch(tag -> tag.contains(expectedTag))
        );
        softly.assertAll();
    }
}



