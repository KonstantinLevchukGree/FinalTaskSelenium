package magentoPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import object.user.User;
import utils.property.PropertyUtil;

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

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get(pageUrl.getProperty("login.page"));
        PageFactory.initElements(driver, this);
    }

    private void inputFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    private void inputLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    private void inputEmail(String email) {
        emailInput.sendKeys(email);
    }

    private void inputAndConfirmationPassword(String password) {
        passwordInput.sendKeys(password);
        passwordConfirmationInput.sendKeys(password);
    }

    public AccountPage openAccountPage(User user) {
        inputFirstName(user.getFirstName());
        inputLastName(user.getLastName());
        inputEmail(user.getEmail());
        inputAndConfirmationPassword(user.getPassword());
        createAccountButton.click();
        return new AccountPage(driver);
    }
}
