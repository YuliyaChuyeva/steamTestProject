package elementFactory;

import core.Driver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public abstract class BaseElement {
    protected By locator;
    private static WebDriver driver;

    public BaseElement(By locator) {
        this.locator = locator;
        driver = Driver.getInstance();
        log.debug("Initialized element with locator: {}", locator);
    }

    protected WebElement getElement() {
        waitForElementPresent();
        log.debug("Found element by locator: {}", locator);
        return driver.findElement(locator);
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
}
