package magentoPage;

import object.product.ProductWishlist;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.singleton.SingletonDriver;
import utils.webDriverWait.ExplicitWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private int random;
    private By gridProducts = By.xpath("//div[contains(@class,'grid products')]//li");

    public ProductsPage() {
        this.driver = SingletonDriver.getInstance();
    }

    private List<WebElement> getProductsFromPage() {
        ExplicitWait.getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(gridProducts));
        return SingletonDriver.getInstance().findElements(gridProducts);
    }

    public ProductWishlist getProductWishlist() {
        List<WebElement> jackets = getProductsFromPage();
        int sizeGrid = jackets.size();
        random = (int) (Math.random() * sizeGrid);
        WebElement jacket = jackets.get(random);
        ProductWishlist productWishlist = new ProductWishlist();
        productWishlist.setName(jacket.findElement(By.xpath(".//strong[contains(@class,'product name')]//a[contains(@class,'product')]"))
                .getText().trim());
        productWishlist.setPrice(jacket.findElement(By.xpath(".//span[@class='price']"))
                .getText().trim());
        return productWishlist;
    }

    public ProductPage openProductPage() {
        List<WebElement> products = getProductsFromPage();
        int sizeGrid = products.size();
        int ran = (int) (Math.random() * sizeGrid);
        WebElement jacket = products.get(ran);
        new Actions(SingletonDriver.getInstance())
                .moveToElement(jacket)
                .pause(Duration.ofSeconds(1))
                .moveToElement(jacket.findElement(By.xpath(".//button[contains(@class,'tocart ')]")))
                .pause(Duration.ofSeconds(1))
                .click()
                .perform();
        return new ProductPage();
    }

    public WishlistPage openWishlistPage() {
        List<WebElement> jackets = getProductsFromPage();
        WebElement jacket = jackets.get(random);
        new Actions(SingletonDriver.getInstance())
                .moveToElement(jacket)
                .pause(Duration.ofSeconds(1))
                .moveToElement(jacket.findElement(By.xpath(".//a[contains(@class,'towishlist')]")))
                .pause(Duration.ofSeconds(1))
                .click()
                .perform();
        return new WishlistPage();
    }
}
