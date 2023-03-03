package magentoPage;

import object.user.User;
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

public class AccountPage {
    private WebDriver driver;
    @FindBy(xpath = "//div[contains(@class,'billing')]//a[contains(@class,'edit')]")
    WebElement editBillingAddressLink;
    @FindBy(xpath = "//a[@id='ui-id-4']")
    WebElement womenMenu;
    @FindBy(xpath = "//a[@id='ui-id-9']")
    WebElement womenTopsMenu;
    @FindBy(xpath = "//a[@id='ui-id-11']")
    WebElement womenTopsJacketsMenu;
    private final String NAME_LABEL = "//div[contains(@class,'information')]//p[contains(text(),'%s')]";

    public AccountPage() {
        this.driver = SingletonDriver.getInstance();
        PageFactory.initElements(driver, this);
    }

    public ProductsPage openProductsPage() {
        ExplicitWait.getExplicitWait().until(ExpectedConditions.visibilityOf(womenMenu));
        new Actions(SingletonDriver.getInstance()).moveToElement(womenMenu)
                .pause(Duration.ofSeconds(1))
                .moveToElement(womenTopsMenu)
                .pause(Duration.ofSeconds(1))
                .moveToElement(womenTopsJacketsMenu)
                .pause(Duration.ofSeconds(1))
                .click()
                .perform();
        return new ProductsPage();
    }

    public User getRegisteredUser(String userName) {
        ExplicitWait.getExplicitWait()
                .until(ExpectedConditions.visibilityOfElementLocated((By.xpath(String.format(NAME_LABEL, userName)))));
        String[] userInfo = SingletonDriver.getInstance().findElement(By.xpath(String.format(NAME_LABEL, userName))).getText().split("[ \\" +
                "n]");
        User user = new User();
        user.setFirstName(userInfo[0].trim());
        user.setLastName(userInfo[1].trim());
        user.setEmail(userInfo[2].trim());
        return user;
    }

    public DefaultAddressPage openDefaultAddressPage() {
        ExplicitWait.getExplicitWait().until(ExpectedConditions.elementToBeClickable(editBillingAddressLink));
        editBillingAddressLink.click();
        return new DefaultAddressPage();
    }
}
