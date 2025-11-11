package service.pages;

import element_factory.BaseElement;
import element_factory.Label;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
public class CategoriesSearchResultPage {
    private static final By GAME_CARDS_WITH_TAG = By.xpath("//div[contains(@class,'Panel') and contains(@class,'Focusable')] //div[@class='ImpressionTrackedElement'][.//a[contains(@href,'/tags/')]]");
    private static final String CARD_TAGS =
            "(//div[contains(@class,'Panel') and contains(@class,'Focusable')]//div[@class='ImpressionTrackedElement'][.//a[contains(@href,'/tags/')]])[%d]"
                    + "//a[contains(@href,'/tags/')]";

    private String normalize(String s) {
        if (s == null) return "";
        return s.toLowerCase(Locale.ROOT)
                .replace('-', ' ')
                .replace('–', ' ')
                .replace('\n', ' ')
                .replaceAll("\\s+", " ")
                .trim();
    }

    public List<Integer> getMismatchedGames(String categoryName, int limit) {
        List<Label> cards = Label.findAll(GAME_CARDS_WITH_TAG);
        if (cards.isEmpty()) {
            log.warn("No cards found - scroll to the bottom of the page..");
            BaseElement.scrollToBottomUntilNoNewElements("//div[contains(@class,'ImpressionTrackedElement')]");
            cards = Label.findAll(GAME_CARDS_WITH_TAG);
        }
        log.info("Found {} cards with tags on the page", cards.size());
        if (cards.isEmpty()) {
            log.error("After scrolling, the cards are still not found.!");
            return List.of();
        }
        int count = Math.min(limit, cards.size());
        String key = normalize(categoryName);
        List<Integer> mismatched = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Label> tags = Label.findAll(By.xpath(String.format(CARD_TAGS, i + 1)));
            List<String> tagTexts = new ArrayList<>();
            for (Label tag : tags) {
                String text = normalize(tag.getText());
                if (!text.isBlank()) tagTexts.add(text);
            }
            boolean hasMatch = tagTexts.stream()
                    .map(this::normalize)
                    .anyMatch(t -> t.equalsIgnoreCase(key) || t.contains(key));
            if (!hasMatch) mismatched.add(i + 1);
        }
        log.info("Cards checked {}, did not match: {}", count, mismatched);
        return mismatched;
    }


}


