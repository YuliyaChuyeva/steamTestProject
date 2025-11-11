package service.pages.menu;

import element_factory.Label;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import service.pages.AbstractPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class CategoriesSubMenu extends AbstractPage {
    private static final By CATEGORY_TILES = By.xpath("//a[contains(@href,'/category/')]");
    private static final By CATEGORY_NAMES = By.xpath("//a[contains(@href,'/category/')]/descendant::div[normalize-space() and not(descendant::*)]");
    private final Random random = new Random();

    public List<String> getAllCategoriesName() {
        List<Label> nameLabels = Label.findAll(CATEGORY_NAMES);
        List<String> names = new ArrayList<>();
        for (Label label : nameLabels) {
            String text = label.getText().trim();
            if (!text.isEmpty()) {
                names.add(text);
            }
        }
        log.info("Found {} categories: {}", names.size(), names);
        return names;
    }

    public String clickRandomCategory() {
        List<Label> nameLabels = Label.findAll(CATEGORY_NAMES);
        List<Label> tiles = Label.findAll(CATEGORY_TILES);
        if (nameLabels.isEmpty() || tiles.isEmpty()) {
            throw new IllegalStateException("Categories not found on page!");
        }
        int index = random.nextInt(Math.min(nameLabels.size(), tiles.size()));
        String categoryName = nameLabels.get(index).getText().trim();
        Label chosenTile = tiles.get(index);
        chosenTile.scrollToElement();
        chosenTile.click();
        log.info("Clicked on the category: {}", categoryName);
        return categoryName;
    }
}
