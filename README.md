# NopCommerce Automation Project

This project automates the checkout process of the demo **nopCommerce** website using **Selenium WebDriver** in a **Java** framework. It follows the **Page Object Model (POM)** design pattern and uses **TestNG** for running tests. Test data is driven from an external **Excel** file, and the project leverages **Apache POI** for reading data.

## Features
- Automates the user checkout process on the demo nopCommerce site.
- Implements the **Page Object Model (POM)** for modular and maintainable code.
- Uses **Data-Driven Testing** with **Apache POI** for reading test data from Excel files.
- Asserts the successful order placement with a "Thank you" message validation.

## Prerequisites
Before you can run the project, ensure you have the following installed:
- **Java Development Kit (JDK)** (version 8 or higher)
- **Maven** for managing dependencies
- **Git** (optional, for cloning the repository)

## Dependencies
The following dependencies are used in this project:
- **Selenium WebDriver**: For browser automation
- **TestNG**: For test execution and reporting
- **Apache POI**: For reading test data from Excel files (.xlsx format)
- **WebDriverManager**: For automatic WebDriver binaries management
- **Maven**: For dependency management

The dependencies are managed via Maven. Here is a list of key dependencies used:

```xml
<!-- Selenium WebDriver -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.25.0</version>
</dependency>

<!-- WebDriverManager -->
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.9.2</version>
</dependency>

<!-- TestNG -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.10.2</version>
    <scope>test</scope>
</dependency>

<!-- org.apache.poi/poi-ooxml -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.5</version>
</dependency>
```

## Project Structure
The project is structured following the **Page Object Model (POM)** design, with the following directories:

- **src/main/java**: Contains the page classes and utilities.
- **src/test/java**: Contains the test classes.
- **src/test/resources**: Contains the test data (Excel file).

```
src/
|-- main/
|   |-- java/
|       |-- pages/
|       |   |-- HomePage.java
|       |   |-- CheckoutPage.java
|       |   |-- CartPage.java
|       |   |-- ThankYouPage.java
|       |-- utils/
|           |-- TestDataProvider.java
|
|-- test/
    |-- java/
    |   |-- tests/
    |   |   |-- TestClass.java
    |
    |-- resources/
        |-- TestData.xlsx
```

## How to Run the Project

### 1. Clone the Repository

You can clone the project repository to your local machine using the following command:

```bash
git clone https://github.com/yourusername/nopcommerce-automation.git
```

### 2. Install Dependencies
Ensure all dependencies are downloaded by running:

```bash
mvn clean install
```

This will download and install all necessary dependencies for the project.

### 3. Update Test Data
Open the `TestData.xlsx` file in the `src/test/resources/` folder and update it with relevant data like First Name, Last Name, Email, Address, etc.

### 4. Run the Tests

You can run the test suite using Maven and TestNG with the following command:

```bash
mvn test
```

Alternatively, you can run the tests directly from your IDE (e.g., IntelliJ IDEA or Eclipse) by running the `CheckoutTest.java` class.

### 5. View Test Results

After execution, TestNG will generate test reports in the `test-output` directory. You can open the `index.html` file inside the `test-output` folder to view the results.

### 6. Browser Configuration
By default, the WebDriver is managed automatically by **WebDriverManager**, which will download the required browser drivers (e.g., ChromeDriver) automatically.

If you need to specify a particular browser or version, you can configure it in the test classes.

## Excel Data Format
The Excel file `TestData.xlsx` contains all test data. The format is as follows:

| FirstName | LastName | Email             | Company    | Country     | State | City   | Address1  | Address2 | ZipPostalCode | PhoneNumber | FaxNumber  |
|-----------|----------|-------------------|------------|-------------|-------|--------|-----------|----------|---------------|-------------|------------|
| Eman      | Nabil    | example@domain.com | SoftTrend | Saudi Arabia | 1     | Riyadh | 22 Street1 | ABC 4C   | 12345         | 1234567890  | 1234567890 |
