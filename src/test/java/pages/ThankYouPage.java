package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ThankYouPage {
    private final WebDriver driver;

    // Constructor
    public ThankYouPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isThankYouMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement thankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='page-title']/h1[text()='Thank you']")));
        return thankYouMessage.isDisplayed();
    }
}
