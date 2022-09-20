import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage  extends  BasePage{

    By shippingOptionLocator =By.xpath("//span[@class=\"a-size-base a-color-base puis-bold-weight-text\"]");

    By productNameLocator =By.xpath("//span[@class=\"a-size-base a-color-base a-text-normal\"]");



    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnProductsPage() {
        return isDisplayed(shippingOptionLocator);
    }

    public void selectProduct(int i) {


        getAllProducts().get(i).click();
    }

    private List<WebElement> getAllProducts(){
        return findAll(productNameLocator);
    }

}
