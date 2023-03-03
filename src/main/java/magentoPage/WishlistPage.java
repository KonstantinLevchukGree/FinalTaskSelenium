package magentoPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import object.product.ProductWishlist;
import utils.singleton.SingletonDriver;

public class WishlistPage {
    private WebDriver driver;
    @FindBy(xpath = "//div[contains(@class,'grid')]//strong[contains(@class,'name')]//a[contains(@class,'product')]")
    WebElement nameProduct;
    @FindBy(xpath = "//div[contains(@class,'grid')]//span[@class='price']")
    WebElement priceProduct;

    public WishlistPage() {
        this.driver = SingletonDriver.getInstance();
        PageFactory.initElements(driver, this);
    }

    public ProductWishlist getProductFromWishList() {
        ProductWishlist productWishlist = new ProductWishlist();
        productWishlist.setName(nameProduct.getText().trim());
        productWishlist.setPrice(priceProduct.getText().trim());
        return productWishlist;
    }
}
