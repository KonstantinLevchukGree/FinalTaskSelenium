package magentoPage;

import object.product.ProductWishlist;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.property.PropertyUtil;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class ProductsPage {
    private WebDriver driver;
    private int random;
    private By gridProducts = By.xpath("//div[contains(@class,'grid products')]//li");
    private final Properties testData = PropertyUtil.getProperties("testsData.properties");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private List<WebElement> getProductsFromPage() {
        new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(testData.getProperty("explicit.time"))))
                .until(ExpectedConditions.visibilityOfElementLocated(gridProducts));
        return driver.findElements(gridProducts);
    }

    public ProductWishlist getProductWishlist() {
        List<WebElement> jackets = getProductsFromPage();
        int sizeGrid = jackets.size();
        random = (int) (Math.random() * sizeGrid);
        WebElement jacket = jackets.get(random);
        ProductWishlist productWishlist = new ProductWishlist();
        productWishlist.setName(jacket.findElement(By.xpath(".//strong[contains(@class,'product name')]//a[contains(@class,'product')]"))
                .getText().trim());
        productWishlist.setPrice(jacket.findElement(By.xpath(".//span[@class='price']")).getText().trim());
        return productWishlist;
    }

    public ProductPage openProductPage() {
        List<WebElement> products = getProductsFromPage();
        int sizeGrid = products.size();
        int ran = (int) (Math.random() * sizeGrid);
        WebElement jacket = products.get(ran);
        new Actions(driver)
                .moveToElement(jacket)
                .pause(Duration.ofSeconds(1))
                .moveToElement(jacket.findElement(By.xpath(".//button[contains(@class,'tocart ')]")))
                .pause(Duration.ofSeconds(1))
                .click()
                .perform();
        return new ProductPage(driver);
    }

    public WishlistPage openWishlistPage() {
        List<WebElement> jackets = getProductsFromPage();
        WebElement jacket = jackets.get(random);
        new Actions(driver)
                .moveToElement(jacket)
                .pause(Duration.ofSeconds(1))
                .moveToElement(jacket.findElement(By.xpath(".//a[contains(@class,'towishlist')]")))
                .pause(Duration.ofSeconds(1))
                .click()
                .perform();
        return new WishlistPage(driver);
    }
}
