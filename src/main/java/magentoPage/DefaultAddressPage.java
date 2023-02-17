package magentoPage;

import object.address.Address;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.property.PropertyUtil;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DefaultAddressPage {
    private WebDriver driver;
    @FindBy(id = "telephone")
    WebElement phoneInput;
    @FindBy(id = "street_1")
    WebElement streetInput;
    @FindBy(id = "city")
    WebElement cityInput;
    @FindBy(id = "region_id")
    WebElement regionSelect;
    @FindBy(id = "zip")
    WebElement zipCodeInput;
    @FindBy(id = "country")
    WebElement countrySelect;
    @FindBy(xpath = "//button[contains(@class,'save')]")
    WebElement saveAddressButton;
    private final Properties testData = PropertyUtil.getProperties("testsData.properties");

    public DefaultAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AddressBookPage openAddressBookPage(Address address) {
        fillDefaultAddressPage(address);
        saveAddressButton.click();
        return new AddressBookPage(driver);
    }

    protected void fillDefaultAddressPage(Address address) {
        inputPhoneNumber(address.getPhone());
        inputStreet(address.getStreet());
        inputCity(address.getCity());
        inputZipCode(address.getZipCode());
        selectCountry(address.getCountry());
        if (driver.findElements(By.xpath("//div[contains(@class,'region')]//label[contains(@for,'id')]"))
                .size() != 0) {
            selectRegion();
        }
    }

    private void inputPhoneNumber(String phoneNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(testData.getProperty("explicit.time"))))
                .until(ExpectedConditions.visibilityOf(phoneInput));
        phoneInput.sendKeys(phoneNumber);
    }

    private void inputStreet(String street) {
        streetInput.sendKeys(street);
    }

    private void inputCity(String city) {
        cityInput.sendKeys(city);
    }

    private void inputZipCode(String code) {
        zipCodeInput.sendKeys(code);
    }

    private void selectCountry(String country) {
        Select select = new Select(countrySelect);
        select.selectByVisibleText(country);
    }

    private void selectRegion() {
        Select select = new Select(regionSelect);
        List<WebElement> allRegions = select.getOptions();
        int size = allRegions.size();
        int random = (int) (Math.random() * ++size) + 1;
        select.selectByIndex(random);
    }

    public Address getRandomAddress() {
        Select select = new Select(countrySelect);
        List<WebElement> allCountries = select.getOptions();
        List<String> countries = new ArrayList<>();
        for (int i = 1; i < allCountries.size(); i++) {
            countries.add(allCountries.get(i).getText());
        }
        int size = countries.size();
        int random = (int) (Math.random() * ++size);
        String country = countries.get(random);

        Faker faker = new Faker();

        return new Address(
                faker.phoneNumber().cellPhone()
                , faker.address().streetName()
                , faker.address().city()
                , faker.address().zipCode()
                , country);
    }
}
