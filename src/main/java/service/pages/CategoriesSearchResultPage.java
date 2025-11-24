package service.pages;

import element_factory.Label;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import service.object.GameCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
public class CategoriesSearchResultPage {
    private static final By GAME_CARDS_WITH_TAG = By.xpath("//div[contains(@class,'ImpressionTrackedElement')]" +
            "[.//a[contains(@href,'/tags/')]]");
    private static final By GAME_CARD_TAGS = By.xpath(".//a[contains(@href,'/tags/')]");
    private static final By GAME_CARD_TITLE = By.xpath("//div[contains(@class,'StoreSaleWidgetShortDesc')]");

    public static String normalize(String s) {
        if (s == null) return "";
        return s.toLowerCase(Locale.ROOT)
                .replace('-', ' ')
                .replace('–', ' ')
                .replace('\n', ' ')
                .replaceAll("\\s+", " ")
                .trim();
    }

    public List<GameCard> getGameCardsOnPage() {
        Label.scrollToBottomUntilNoNewElements("//div[contains(@class,'ImpressionTrackedElement')]");
        List<Label> cardRoots = Label.findAll(GAME_CARDS_WITH_TAG);
        if (cardRoots.isEmpty()) {
            log.warn("No game cards found on page by locator: {}", GAME_CARDS_WITH_TAG);
            return List.of();
        }
        log.info("Found {} game cards with tags on page", cardRoots.size());
        return buildGameCards(cardRoots);
    }

    private List<GameCard> buildGameCards(List<Label> cardRoots) {
        List<GameCard> cards = new ArrayList<>(cardRoots.size());
        for (int i = 0; i < cardRoots.size(); i++) {
            int position = i + 1;
            WebElement root = cardRoots.get(i).getElement();
            String title = extractTitle(root);
            List<String> tags = extractTags(root);
            log.debug("Card {}: '{}' -> tags: {}", position, title, tags);
            cards.add(new GameCard(title, tags));
        }
        return cards;
    }

    private String extractTitle(WebElement cardRoot) {
        List<WebElement> titleElements = cardRoot.findElements(GAME_CARD_TITLE);
        String title = normalize(titleElements.get(0).getText());
        return title.isBlank() ? "UnknownTitle" : title;
    }

    private List<String> extractTags(WebElement cardRoot) {
        return cardRoot.findElements(GAME_CARD_TAGS)
                .stream()
                .map(WebElement::getText)
                .map(CategoriesSearchResultPage::normalize)
                .filter(s -> !s.isBlank())
                .toList();
    }
}

