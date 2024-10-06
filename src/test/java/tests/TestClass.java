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

import java.io.IOException;
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

    @DataProvider(name = "testData")
    public Object[][] testData() throws IOException {
        return TestDataProvider.readExcel("src/test/resources/TestData.xlsx", "Sheet1");
    }

    @Test(dataProvider = "testData")
    public void testCheckoutProcess(String firstName, String lastName, String email, String company,
                                    String country, String state, String city, String address1,
                                    String address2, String zip, String phone, String fax) throws InterruptedException {
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
        checkoutPage.fillBillingInformation(firstName, lastName, email, company, country, state, city, address1, address2, zip, phone, fax);
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
