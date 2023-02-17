package magentoPage;

import object.product.ProductCart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.property.PropertyUtil;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

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
    private By showCartAction = By.xpath("//a[(@class='action showcart')]");
    private By editCartButton = By.xpath("//a[@class='action viewcart']");
    private final Properties testData = PropertyUtil.getProperties("testsData.properties");

    private void openMenu() {
        new Actions(driver)
                .moveToElement(driver.findElement(showCartAction))
                .pause(Duration.ofSeconds(1))
                .click()
                .perform();
    }

    public CartPage openCartPage() {
        openMenu();
        new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(testData.getProperty("explicit.time"))))
                .until(ExpectedConditions.elementToBeClickable(editCartButton));
        driver.findElement(editCartButton).click();
        return new CartPage(driver);
    }

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductsPage backProductsPage() {
        driver.navigate().back();
        return new ProductsPage(driver);
    }

    public void addedProductToCart() {
        new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(testData.getProperty("explicit.time"))))
                .until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
    }

    public ProductCart getProduct() {


        new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(testData.getProperty("explicit.time"))))
                .until(ExpectedConditions.textToBePresentInElement(productPrice, "$"));

        ProductCart product = new ProductCart();
        product.setSize(choiceRandomSize());
        product.setColor(choiceRandomColor());
        product.setName(productName.getText());
        product.setPrice(productPrice.getText());
        return product;
    }

    private String choiceRandomColor() {
        List<WebElement> listColors = getColorOptions();
        int sizeList = listColors.size();
        int random = (int) (Math.random() * sizeList);
        WebElement color = listColors.get(random);
        color.click();
        return color.getAttribute("option-label");
    }

    private String choiceRandomSize() {
        List<WebElement> listSizes = getSizeOption();
        int sizeList = listSizes.size();
        int random = (int) (Math.random() * sizeList);
        WebElement size = listSizes.get(random);
        size.click();
        return size.getText();
    }

    private List<WebElement> getSizeOption() {
        new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(testData.getProperty("explicit.time"))))
                .until(ExpectedConditions.visibilityOf(sizeOptions));
        return sizeOptions.findElements(By.xpath(".//div[contains(@class,'option')]"));
    }

    private List<WebElement> getColorOptions() {
        return colorOptions.findElements(By.xpath(".//div[contains(@class,'option')]"));
    }
}
