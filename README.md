# POM_Selenium_Amazon
<a href="https://selenium.dev"><img src="https://selenium.dev/images/selenium_logo_square_green.png" width="180" alt="Selenium"/></a>

# Hedef
Creating an order on the goal Amazon website
## Video



https://user-images.githubusercontent.com/57863133/192131761-84f21102-6a42-4c62-9e15-55e8549674cf.mp4



# Selenium



Selenium is a portable software testing framework for web applications. It also provides a test site-specific language for writing tests in several popular programming languages, including Java, C#, Groovy, Perl, PHP, Python, and Ruby.

  * [Use PageObjects Pattern](#use-pageobjects-pattern)
  * [Video](#Video)
  * [Scenario](#Senaryo)
  * [TestClass](#TesClass)
  * [Base_Test](#Base_Test)
  * [BasePage](#BasePage)
  
 
## Use PageObjecect Pattern

Page Object is a Design Pattern that has become popular in test automation to improve test maintenance and reduce code duplication. An implementation of the sheet object model can be accomplished by separating the abstraction of the test object with the test scripts.

**Advantages of using the Page Object Overlay:**
* Easy to maintain
* Easy Readability of scripts
* Reduce or Eliminate Duplication
* Code reusability
* Reliability
* There is a clear distinction between test code and page-specific code such as locators and layout.


 
## Scenario
* User goto "https://www.amazon.com.tr/"
* If a cookie pops up, the user presses the accept button.
* Search for Laptop in the search box.
* user clicks on one of the opened products.
* The user clicks the add to cart button on the product detail page that opens.
* User checks if the product has been added to the cart.
* User completes the transaction.



## Test Class

```java

public class Test_Add_Product_To_Cart extends Base_Test {

    HomePage homePage;
    ProductsPage productsPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;

    @Test
    @Order(1)
    public void search_a_product(){
        homePage = new HomePage(driver);
        homePage.acceptCookies();
        productsPage = new ProductsPage(driver);
        homePage.searchBox().search("Laptop");
        Assertions.assertTrue(productsPage.isOnProductsPage(),
                "Not on products page");

}
    @Test
    @Order(2)
    public void select_a_product(){

        productDetailPage = new ProductDetailPage(driver);
        productsPage.selectProduct(1);
        Assertions.assertTrue(productDetailPage.isOnProductDetailPage(),
                "Not on product detail page!");


    }
    @Test
    @Order(3)
    public void add_product_to_cart(){

        productDetailPage.addToCart();
        Assertions.assertTrue(homePage.isProductCountUp(),
                "Product count didn't increase!");

    }
    @Test
    @Order(4)
    public void go_to_cart(){
        cartPage = new CartPage(driver);
        homePage.goToCart();
        Assertions.assertTrue(cartPage.checkIfProductAdded(),
        "product wasn't added cart");

    }
}

```


## Bese_Test

```java

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class )
@ExtendWith(TestResultLogger.class)
public class Base_Test {


    WebDriver driver;
    @BeforeAll
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com.tr/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @AfterAll

    public void tearDown(){
        driver.quit();
    }
}

```

## BasePage


```java


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {

    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver=driver;
    }
    public WebElement find(By locator){
        return driver.findElement(locator);
    }
    public List<WebElement>  findAll(By locator){
        return driver.findElements(locator);
    }

    public void click(By locator){
        find(locator).click();
    }

    public void type(By locator, String text){
        find(locator).sendKeys(text);
    }

    public Boolean isDisplayed(By locator){
        return find(locator).isDisplayed();
    }


}


```
