package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.*;

import java.io.IOException;
import java.time.Duration;

public class TestClass {
    private WebDriver driver;
    private BrowserDriverFactory browserFactory;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        String browser = System.getProperty("browser", "chrome"); // Default to chrome if no parameter
        BrowserDriverFactory.BrowserType type = BrowserDriverFactory.BrowserType.valueOf(browser.toUpperCase());
        browserFactory = new BrowserDriverFactory(type);

        // Create WebDriver instance
        driver = browserFactory.createDriver();
        driver.manage().window().maximize();

        // Create Wait instance
        wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Wait for up to 50 seconds
    }

    @DataProvider(name = "testData")
    public Object[][] testData() throws IOException {
        return TestDataProvider.readExcel("src/test/resources/TestData.xlsx", "Sheet1");
    }

    @Test
    public void tc01_AddProductToCart() throws InterruptedException {
        // Initialize page objects
        ProductPage productPage = new ProductPage(driver);

        // Add product to cart and navigate to the cart
        productPage.addProductToCart();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'The product has been added to your')]")));

        productPage.navigateToCart();

        // Verify if the cart page is loaded
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://demo.nopcommerce.com/cart")));
    }

    @Test(dependsOnMethods = "tc01_AddProductToCart")
    public void tc02_ProceedToCheckout() throws InterruptedException {
        // Initialize page objects
        CartPage cartPage = new CartPage(driver);

        // Accept terms and proceed to checkout
        cartPage.acceptTermsAndProceed();
    }

    @Test(dependsOnMethods = "tc02_ProceedToCheckout", dataProvider = "testData")
    public void tc03_EnterBillingAndShippingInfo(String firstName, String lastName, String email, String company,
                                                String country, String state, String city, String address1,
                                                String address2, String zip, String phone, String fax) throws InterruptedException {
        // Initialize page objects
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Fill billing information
        checkoutPage.fillBillingInformation(firstName, lastName, email, company, country, state, city, address1, address2, zip, phone, fax);
        Thread.sleep(1000);

        // Proceed through the checkout process
        checkoutPage.proceedToShipping();
        Thread.sleep(1000);

        checkoutPage.proceedToPayment();
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods = "tc03_EnterBillingAndShippingInfo")
    public void tc04_testCompleteOrder() throws InterruptedException {
        // Initialize page objects
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ThankYouPage thankYouPage = new ThankYouPage(driver);

        // Confirm the order
        checkoutPage.confirmOrder();
        Thread.sleep(1000);

        // Verify Thank You message
        Assert.assertTrue(thankYouPage.isThankYouMessageDisplayed(), "Thank you message not displayed!");
    }

    @AfterClass
    public void CloseBrowser() {
        browserFactory.quitDriver();
    }
}
