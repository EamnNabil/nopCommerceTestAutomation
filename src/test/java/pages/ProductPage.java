package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    private final WebDriver driver;

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to add a product to the cart
    public void addProductToCart() {
        driver.get("https://demo.nopcommerce.com/desktops");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement addToCartButton = getAddToCartButton();

        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", addToCartButton);
        addToCartButton.click();
    }

    // Method to get the success message
    public String getSuccessMessage() {
        return getSuccessMessageElement().getText();
    }

    // Method to navigate to the shopping cart
    public void navigateToCart() {
        getShoppingCartLink().click();
    }

    // Private methods to get WebElement references
    private WebElement getAddToCartButton() {
        return driver.findElement(By.xpath("//div[@class='product-item' and @data-productid='2']//button[contains(@class, 'product-box-add-to-cart-button')]"));
    }

    private WebElement getSuccessMessageElement() {
        return driver.findElement(By.xpath("//p[contains(text(), 'The product has been added to your')]"));
    }

    private WebElement getShoppingCartLink() {
        return driver.findElement(By.linkText("shopping cart"));
    }
}

