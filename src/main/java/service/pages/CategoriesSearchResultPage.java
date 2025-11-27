package service.pages;

import core.StringUtil;
import element_factory.Label;
import lombok.extern.slf4j.Slf4j;
import service.object.GameCard;

import java.util.List;

@Slf4j
public class CategoriesSearchResultPage {
    private final Label gameCardsContainer = new Label("//div[contains(@class,'ImpressionTrackedElement')]");
    private final Label cardWithTagsLocator = new Label("//div[contains(@class,'ImpressionTrackedElement')][.//a[contains(@href,'/tags/')]]");
    private final String GAME_CARD_TAGS = ".//a[contains(@href,'/tags/')]";
    private final String GAME_CARD_TITLE = ".//div[contains(@class,'StoreSaleWidgetShortDesc')]";

    public List<GameCard> getGameCardsOnPage() {
        gameCardsContainer.scrollToBottomUntilNoNewElements();
        List<Label> cards = cardWithTagsLocator.findAll();
        if (cards.isEmpty()) {
            log.warn("No game cards found on page");
            return List.of();
        }
        log.info("Found {} game cards with tags", cards.size());
        return cards.stream()
                .map(this::buildGameCard)
                .toList();
    }

    private GameCard buildGameCard(Label cardElement) {
        String title = extractTitle(cardElement);
        List<String> tags = extractTags(cardElement);
        return new GameCard(title, tags);
    }

    private String extractTitle(Label cardElement) {
        List<Label> titles = cardElement.findAll(GAME_CARD_TITLE);
        String title = StringUtil.normalizeText(titles.get(0).getText());
        return title.isBlank() ? "UnknownTitle" : title;
    }

    private List<String> extractTags(Label cardElement) {
        return cardElement.findAll(GAME_CARD_TAGS).stream()
                .map(Label::getText)
                .map(StringUtil::normalizeText)
                .filter(s -> !s.isBlank())
                .toList();
    }
}

