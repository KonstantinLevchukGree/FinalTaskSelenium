package magentoPage;

import object.address.Address;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.singleton.SingletonDriver;

public class AddressBookPage {
    private WebDriver driver;
    @FindBy(xpath = "//div[contains(@class,'billing')]")
    WebElement billingBox;

    public AddressBookPage() {
        this.driver = SingletonDriver.getInstance();
        PageFactory.initElements(driver, this);
    }

    public Address getAddress() {
        String infoAddress = billingBox.findElement(By.xpath(".//address")).getText();
        String[] lines = infoAddress.split("\n");
        Address address = new Address(lines[4].replace("T: ", "").trim()
                , lines[1].trim()
                , lines[2].split(",")[0].trim()
                , lines[2].split(",")[1].trim()
                , lines[3].trim());
        return address;
    }
}
