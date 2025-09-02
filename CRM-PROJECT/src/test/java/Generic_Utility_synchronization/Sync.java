package Generic_Utility_synchronization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Generic_Utility_Synchronisation.Synchronisation;

public class Sync {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        Synchronisation util = new Synchronisation(driver);

        driver.get("https://www.facebook.com/");

        // Set implicit wait
        util.setImplicitWait(10);

        // Use explicit wait to wait for username field
        WebElement username = util.waitForElementToBeVisible(By.id("username"), 15);
        username.sendKeys("testuser");

        // Wait for password and send keys
        WebElement password = util.waitForElementToBeClickable(By.id("password"), 10);
        password.sendKeys("password123");

        // Wait for login button using fluent wait
        WebElement loginBtn = util.fluentWait(By.id("loginBtn"), 20, 500);
        loginBtn.click();

        driver.quit();
    }
}
