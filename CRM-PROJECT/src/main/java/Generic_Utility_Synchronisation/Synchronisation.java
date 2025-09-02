package Generic_Utility_Synchronisation;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.function.Function;

public class Synchronisation {

    WebDriver driver;

    // Constructor
    public Synchronisation(WebDriver driver) {
        this.driver = driver;
    }

    // 1. Implicit Wait (applied globally)
    public void setImplicitWait(int timeInSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
    }

    // 2. Explicit Wait - Wait for element to be visible
    public WebElement waitForElementToBeVisible(By locator, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // 3. Explicit Wait - Wait for element to be clickable
    public WebElement waitForElementToBeClickable(By locator, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // 4. Explicit Wait - Wait for title to contain text
    public boolean waitForTitleContains(String Title, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        return wait.until(ExpectedConditions.titleContains(Title));
    }

    // 5. Fluent Wait - Customized wait for a condition
    public WebElement fluentWait(final By locator, int timeoutInSeconds, int pollingInMillis) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingInMillis))
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }
}
