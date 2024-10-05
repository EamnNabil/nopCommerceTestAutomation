package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage {
    private final WebDriver driver;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to fill billing information
    public void fillBillingInformation(String firstName, String lastName, String email, String company, String country, String state, String city, String address1, String address2, String zip, String phone, String fax) {
        getFirstNameInput().sendKeys(firstName);
        getLastNameInput().sendKeys(lastName);
        getEmailInput().sendKeys(email);
        getCompanyInput().sendKeys(company);

        // Select Country and State (Assuming you have dropdown logic)
        selectCountry(country);
        selectState(state);

        getCityInput().sendKeys(city);
        getAddress1Input().sendKeys(address1);
        getAddress2Input().sendKeys(address2);
        getZipPostalCodeInput().sendKeys(zip);
        getPhoneNumberInput().sendKeys(phone);
        getFaxNumberInput().sendKeys(fax);

        // Click the continue button
        getBillingContinueButton().click();
    }

    // Method to proceed through the checkout steps
    public void proceedToShipping() {
        getShippingContinueButton().click();
    }

    public void proceedToPayment() {
        getPaymentContinueButton().click();
    }

    public void confirmOrder() {
        WebElement confirmButton = getConfirmButton();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmButton);
        confirmButton.click();
    }

    // Private methods to get WebElement references
    private WebElement getFirstNameInput() {
        return driver.findElement(By.id("BillingNewAddress_FirstName"));
    }

    private WebElement getLastNameInput() {
        return driver.findElement(By.id("BillingNewAddress_LastName"));
    }

    private WebElement getEmailInput() {
        return driver.findElement(By.id("BillingNewAddress_Email"));
    }

    private WebElement getCompanyInput() {
        return driver.findElement(By.id("BillingNewAddress_Company"));
    }

    private WebElement getCityInput() {
        return driver.findElement(By.id("BillingNewAddress_City"));
    }

    private WebElement getAddress1Input() {
        return driver.findElement(By.id("BillingNewAddress_Address1"));
    }

    private WebElement getAddress2Input() {
        return driver.findElement(By.id("BillingNewAddress_Address2"));
    }

    private WebElement getZipPostalCodeInput() {
        return driver.findElement(By.id("BillingNewAddress_ZipPostalCode"));
    }

    private WebElement getPhoneNumberInput() {
        return driver.findElement(By.id("BillingNewAddress_PhoneNumber"));
    }

    private WebElement getFaxNumberInput() {
        return driver.findElement(By.id("BillingNewAddress_FaxNumber"));
    }

    private WebElement getBillingContinueButton() {
        return driver.findElement(By.xpath("//button[@name='save' and contains(@class, 'new-address-next-step-button')]"));
    }

    private WebElement getShippingContinueButton() {
        return driver.findElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button' and @type='button']"));
    }

    private WebElement getPaymentContinueButton() {
        return driver.findElement(By.xpath("//button[@class='button-1 payment-method-next-step-button' and @type='button']"));
    }

    private WebElement getConfirmButton() {
        return driver.findElement(By.xpath("//button[@class='button-1 confirm-order-next-step-button']"));
    }

    private void selectCountry(String countryValue) {
        WebElement countryDropdown = driver.findElement(By.id("BillingNewAddress_CountryId"));
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByValue(countryValue);
    }

    private void selectState(String stateValue) {
        WebElement stateDropdown = driver.findElement(By.id("BillingNewAddress_StateProvinceId"));
        Select selectState = new Select(stateDropdown);
        selectState.selectByValue(stateValue);
    }
}
