import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Stack;

public class HomePage extends BasePage {

    SearchBox searchBox;
    By cartCountLocator = By.id("nav-cart-count");

    By cartContainerLocator = By.xpath("//div[@id='nav-cart-text-container']/span[@class='nav-line-1']");

    By acceptCookiesLocator = By.id("sp-cc-accept");

    public HomePage(WebDriver driver) {
        super(driver);
        searchBox = new SearchBox(driver);
    }

    public SearchBox searchBox() {
        return this.searchBox;
    }

    public boolean isProductCountUp() {

        return getCartCount()>0;

    }

    public void goToCart() {
        click(cartContainerLocator);
    }

    private int getCartCount(){
       String count= find(cartCountLocator).getText();
        return  Integer.parseInt(count);
    }

    public void acceptCookies(){
        if (isDisplayed(acceptCookiesLocator)){
            click(acceptCookiesLocator );
        }
    }
}
