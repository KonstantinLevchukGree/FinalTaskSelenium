package magentoPage;

import com.github.javafaker.Faker;
import object.address.Address;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.singleton.SingletonDriver;
import utils.webDriverWait.ExplicitWait;

import java.util.ArrayList;
import java.util.List;

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

    public DefaultAddressPage() {
        this.driver = SingletonDriver.getInstance();
        PageFactory.initElements(driver, this);
    }

    public AddressBookPage openAddressBookPage(Address address) {
        fillDefaultAddressPage(address);
        saveAddressButton.click();
        return new AddressBookPage();
    }

    private void fillDefaultAddressPage(Address address) {
        ExplicitWait.getExplicitWait().until(ExpectedConditions.visibilityOf(phoneInput));
        phoneInput.sendKeys(address.getPhone());
        streetInput.sendKeys(address.getStreet());
        cityInput.sendKeys(address.getCity());
        zipCodeInput.sendKeys(address.getZipCode());
        selectCountry(address.getCountry());
        if (SingletonDriver.getInstance().findElements(By.xpath("//div[contains(@class,'region')]//label[contains(@for,'id')]"))
                .size() != 0) {
            selectRandomRegion();
        }
    }

    private void selectCountry(String country) {
        Select select = new Select(countrySelect);
        select.selectByVisibleText(country);
    }

    private void selectRandomRegion() {
        Select select = new Select(regionSelect);
        List<WebElement> allRegions = select.getOptions();
        int size = allRegions.size();
        int random = (int) (Math.random() * ++size) + 1;
        select.selectByIndex(random);
    }

    public Address createRandomAddress() {
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
