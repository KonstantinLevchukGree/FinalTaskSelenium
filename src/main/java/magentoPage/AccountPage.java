package magentoPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import object.user.User;
import utils.property.PropertyUtil;

import java.time.Duration;
import java.util.Properties;

public class AccountPage {
    private WebDriver driver;
    @FindBy(xpath = "//div[contains(@class,'billing')]//a[contains(@class,'edit')]")
    WebElement editBillingAddressLink;
    private By womenMenu = By.xpath("//a[@id='ui-id-4']");
    private By womenTopsMenu = By.xpath("//a[@id='ui-id-9']");
    private By womenTopsJacketsMenu = By.xpath("//a[@id='ui-id-11']");
    private final String NAME_LABEL = "//div[contains(@class,'information')]//p[contains(text(),'%s')]";
    private final Properties testData = PropertyUtil.getProperties("testsData.properties");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductsPage openProductsPage() {
        new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(testData.getProperty("explicit.time"))))
                .until(ExpectedConditions.visibilityOfElementLocated(womenMenu));
        new Actions(driver).moveToElement(driver.findElement(womenMenu))
                .pause(Duration.ofSeconds(1))
                .moveToElement(driver.findElement(womenTopsMenu))
                .pause(Duration.ofSeconds(1))
                .moveToElement(driver.findElement(womenTopsJacketsMenu))
                .pause(Duration.ofSeconds(1))
                .click()
                .perform();
        return new ProductsPage(driver);
    }
    public User getRegisteredUser(String userName) {
        new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(testData.getProperty("explicit.time"))))
                .until(ExpectedConditions.visibilityOfElementLocated((By.xpath(String.format(NAME_LABEL, userName)))));

        String[] userInfo = driver.findElement(By.xpath(String.format(NAME_LABEL, userName))).getText().split("[ \\" +
                "n]");
        User user = new User();
        user.setFirstName(userInfo[0]);
        user.setLastName(userInfo[1]);
        user.setEmail(userInfo[2]);
        return user;
    }

    public DefaultAddressPage openDefaultAddressPage() {
        new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(testData.getProperty("explicit.time"))))
                .until(ExpectedConditions.elementToBeClickable(editBillingAddressLink));
        editBillingAddressLink.click();
        return new DefaultAddressPage(driver);
    }
}
