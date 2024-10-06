package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.junit.Assert;
import pages.*;
import utils.*;

import java.time.Duration;

public class TestClass {
    private WebDriver driver;
    private BrowserDriverFactory browserFactory;

    @BeforeClass
    public void setup() {
        String browser = System.getProperty("browser", "chrome"); // Default to chrome if no parameter
        BrowserDriverFactory.BrowserType type = BrowserDriverFactory.BrowserType.valueOf(browser.toUpperCase());
        browserFactory = new BrowserDriverFactory(type);

        // Create WebDriver instance
        driver = browserFactory.createDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testCheckoutProcess() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Wait for up to 50 seconds

        // Initialize page objects
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ThankYouPage thankYouPage = new ThankYouPage(driver);

        // Add product to cart and navigate to the cart
        productPage.addProductToCart();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'The product has been added to your')]")));

        productPage.navigateToCart();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://demo.nopcommerce.com/cart")));

        // Accept terms and proceed
        cartPage.acceptTermsAndProceed();

        // Fill billing information
        checkoutPage.fillBillingInformation("Eman", "Nabil", "e@email.com", "SoftTrend", "220", "1", "Cairo","12345", "123-222", "11234", "123-456-7890", "123-456-7890");
        Thread.sleep(1000);

        // Proceed through the checkout process
        checkoutPage.proceedToShipping();
        Thread.sleep(1000);

        checkoutPage.proceedToPayment();
        Thread.sleep(1000);

        checkoutPage.confirmOrder();
        Thread.sleep(1000);

        // Verify Thank You message
        Assert.assertTrue("Thank you message not displayed!", thankYouPage.isThankYouMessageDisplayed());
    }

    @AfterClass
    public void CloseBrowser() {
        browserFactory.quitDriver();
    }
}
