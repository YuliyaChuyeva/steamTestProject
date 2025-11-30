package element_factory;

import core.Driver;
import core.Waiter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Slf4j
public abstract class BaseElement {
    protected By locator;
    private static WebDriver driver;

    public BaseElement(By locator) {
        this.locator = locator;
        driver = Driver.getInstance();
        log.debug("Initialized element with locator: {}", locator);
    }

    public WebElement getElement() {
        waitForElementPresent();
        log.debug("Found element by locator: {}", locator);
        return driver.findElement(locator);
    }
    public String getAttribute(String name){
        return getVisibleElement().getAttribute(name);
    }

    public String getText() {
        String text = getElement().getText();
        log.info("Got text '{}' from element: {}", text, locator);
        return text;
    }

    public void waitForElementPresent() {
        log.debug("Waiting for presence of element:{}", locator);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForClickability() {
        log.debug("Waiting for element to be clickable: {}", locator);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void clickAction() {
        waitForClickability();
        new Actions(driver).moveToElement(getElement()).click().perform();
        log.info("Clicked Actions:{}", locator);
    }

    public void click() {
        waitForClickability();
        getElement().click();
        log.info("Clicked on element:{}", locator);
    }

    public void waitForVisibility() {
        new WebDriverWait(Driver.getInstance(), Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void scrollToElement() {
        WebElement element = getElement();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        log.info("Scrolled to element: {}", locator);
    }

    public int scrollToBottomUntilNoNewElements() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int count = 0;
        for (int i = 1; i <= 10; i++) {
            List<WebElement> elements = findElements();
            count = elements.size();
            if (count > 0) {
                WebElement last = elements.get(count - 1);
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'end'});", last);
            } else {
                ((JavascriptExecutor) driver)
                        .executeScript("window.scrollTo(0, document.body.scrollHeight);");
            }
            Waiter.waitForAjax();
            Waiter.waitForPageToLoad();
            try {
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, count));
            } catch (TimeoutException e) {
                break;
            }
        }
        return findElements().size();
    }

    protected List<WebElement> findElements() {
        List<WebElement> elements = driver.findElements(locator);
        log.debug("Found {} elements by locator: {}", elements.size(), locator);
        return elements;
    }

    protected String getXPath() {
        String raw = locator.toString();
        if (!raw.startsWith("By.xpath: ")) {
            throw new IllegalStateException(
                    "Only XPath locators are supported, got: " + raw
            );
        }
        return raw.substring("By.xpath: ".length()).trim();
    }

    protected WebElement getVisibleElement() {
        waitForVisibility();
        return Driver.getInstance().findElement(locator);
    }
}


