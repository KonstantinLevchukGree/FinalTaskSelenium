package magentoPage;

import object.product.ProductCart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.singleton.SingletonDriver;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    @FindBy(id = "shopping-cart-table")
    WebElement productTable;

    public CartPage() {
        this.driver = SingletonDriver.getInstance();
        PageFactory.initElements(driver, this);
    }

    public List<ProductCart> getAllProducts() {
        List<ProductCart> productsCart = new ArrayList<>();
        List<WebElement> products;
        products = productTable.findElements(By.xpath(".//tbody"));
        for (WebElement product : products) {
            ProductCart productCart = new ProductCart();
            productCart.setName(product
                    .findElement(By.xpath(".//strong[contains(@class,'name')]/a")).getText().trim());
            productCart.setPrice(product
                    .findElement(By.xpath(".//td[contains(@class,'col price')]//span[@class='price']")).getText().trim());
            productCart.setSize(product.
                    findElement(By.xpath(".//dl[contains(@class,'options')]//dd[1]")).getText().trim());
            productCart.setColor(product.
                    findElement(By.xpath(".//dl[contains(@class,'options')]//dd[2]")).getText().trim());
            productsCart.add(productCart);
        }
        return productsCart;
    }
}
