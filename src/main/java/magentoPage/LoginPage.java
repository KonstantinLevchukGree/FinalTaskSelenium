package magentoPage;

import object.user.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.property.PropertyUtil;
import utils.singleton.SingletonDriver;

import java.util.Properties;

public class LoginPage {
    private WebDriver driver;
    @FindBy(id = "firstname")
    WebElement firstNameInput;
    @FindBy(id = "lastname")
    WebElement lastNameInput;
    @FindBy(id = "email_address")
    WebElement emailInput;
    @FindBy(id = "password")
    WebElement passwordInput;
    @FindBy(id = "password-confirmation")
    WebElement passwordConfirmationInput;
    @FindBy(xpath = "//button[contains(@class,'submit')]")
    WebElement createAccountButton;
    private final Properties pageUrl = PropertyUtil.getProperties("pageUrl.properties");

    public LoginPage() {
        this.driver = SingletonDriver.getInstance();
        this.driver.get(pageUrl.getProperty("login.page"));
        PageFactory.initElements(driver, this);
    }

    private void inputAndConfirmationPassword(String password) {
        passwordInput.sendKeys(password);
        passwordConfirmationInput.sendKeys(password);
    }

    public AccountPage openAccountPage(User user) {
        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        emailInput.sendKeys(user.getEmail());
        inputAndConfirmationPassword(user.getPassword());
        createAccountButton.click();
        return new AccountPage();
    }
}
