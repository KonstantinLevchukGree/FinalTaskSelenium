package magentoPage;

import object.product.ProductCart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.singleton.SingletonDriver;
import utils.webDriverWait.ExplicitWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    @FindBy(id = "product-addtocart-button")
    WebElement addToCartButton;
    @FindBy(xpath = "//span[contains(@class,'base')]")
    WebElement productName;
    @FindBy(xpath = "//div[contains(@class,'info-main')]//span[@class='price']")
    WebElement productPrice;
    @FindBy(xpath = "//div[contains(@class,'size')]//div[contains(@class,'options')]")
    WebElement sizeOptions;
    @FindBy(xpath = "//div[contains(@class,'color')]//div[contains(@class,'options')]")
    WebElement colorOptions;
    @FindBy(xpath = "//a[(@class='action showcart')]")
    WebElement showCartAction;
    @FindBy(xpath = "//a[@class='action viewcart']")
    WebElement editCartButton;
    private By options = By.xpath(".//div[contains(@class,'option')]");

    public ProductPage() {
        this.driver = SingletonDriver.getInstance();
        PageFactory.initElements(driver, this);
    }

    private void openMenu() {
        new Actions(SingletonDriver.getInstance())
                .moveToElement(showCartAction)
                .pause(Duration.ofSeconds(1))
                .click()
                .perform();
    }

    public CartPage openCartPage() {
        openMenu();
        ExplicitWait.getExplicitWait().until(ExpectedConditions.elementToBeClickable(editCartButton));
        editCartButton.click();
        return new CartPage();
    }

    public void backProductsPage() {
        SingletonDriver.getInstance().navigate().back();
        new ProductsPage();
    }

    public void addedProductToCart() {
        ExplicitWait.getExplicitWait().until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
    }

    public ProductCart getProduct() {
        ExplicitWait.getExplicitWait().until(ExpectedConditions.textToBePresentInElement(productPrice, "$"));
        ProductCart product = new ProductCart();
        product.setSize(choiceRandomSize());
        product.setColor(choiceRandomColor());
        product.setName(productName.getText());
        product.setPrice(productPrice.getText());
        return product;
    }

    private String choiceRandomColor() {
        List<WebElement> listColors = colorOptions.findElements(options);
        int sizeList = listColors.size();
        int random = (int) (Math.random() * sizeList);
        WebElement color = listColors.get(random);
        color.click();
        return color.getAttribute("option-label");
    }

    private String choiceRandomSize() {
        ExplicitWait.getExplicitWait().until(ExpectedConditions.visibilityOf(sizeOptions));
        List<WebElement> listSizes = sizeOptions.findElements(options);
        int sizeList = listSizes.size();
        int random = (int) (Math.random() * sizeList);
        WebElement size = listSizes.get(random);
        size.click();
        return size.getText();
    }
}
