package service.pages.menu;

import element_factory.Label;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import service.pages.AbstractPage;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
public class CategoriesSubMenu extends AbstractPage {
    private final String CATEGORY_LINK = "//a[contains(@href,'/category/')]";
    private final String CATEGORY_NAME = "//a[contains(@href,'/category/')]/descendant::div[normalize-space() and not(descendant::*)]";
    private final Random random = new Random();

    public List<String> getAllCategoriesName() {
        List<Label> categoryNamesLabels = new Label(CATEGORY_NAME).findAll();
        List<String> names = categoryNamesLabels.stream()
                .map(Label::getText)
                .filter(t -> !t.isEmpty())
                .toList();
        log.info("Found {} categories: {}", names.size(), names);
        return names;
    }

    @Step("Click random category")
    public String clickRandomCategory() {
        List<Label> categoryLinks = new Label(CATEGORY_LINK).findAll();
        List<Label> categoryNameLabels = new Label(CATEGORY_NAME).findAll();
        int categoriesCount = Math.min(categoryLinks.size(), categoryNameLabels.size());
        if (categoriesCount == 0) throw new IllegalStateException("No categories on page!");
        List<Integer> indexesWithName = IntStream.range(0, categoriesCount)
                .filter(i -> !categoryNameLabels.get(i).getText().isBlank())
                .boxed()
                .toList();
        if (indexesWithName.isEmpty()) {
            throw new IllegalStateException("All category names are empty!");
        }
        int randomIndex = indexesWithName.get(random.nextInt(indexesWithName.size()));
        Label categoryLink = categoryLinks.get(randomIndex);
        String categoryName = categoryNameLabels.get(randomIndex).getText().trim();
        categoryLink.scrollToElement();
        categoryLink.click();
        log.info("Clicked category: {}", categoryName);
        return categoryName;
    }

}

