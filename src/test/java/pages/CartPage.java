package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to accept terms and proceed to checkout
    public void acceptTermsAndProceed() {
        WebElement termsCheckbox = getTermsCheckbox();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", termsCheckbox);
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }

        getCheckoutButton().click();
    }

    // Private methods to get WebElement references
    private WebElement getTermsCheckbox() {
        return driver.findElement(By.id("termsofservice"));
    }

    private WebElement getCheckoutButton() {
        return driver.findElement(By.id("checkout"));
    }
}
