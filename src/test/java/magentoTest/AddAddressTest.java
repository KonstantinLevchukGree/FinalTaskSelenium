package magentoTest;

import object.address.Address;
import magentoPage.AccountPage;
import magentoPage.AddressBookPage;
import magentoPage.DefaultAddressPage;
import magentoPage.LoginPage;
import org.junit.jupiter.api.Test;
import utils.singleton.SingletonInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddAddressTest extends BaseTest {

    @Test
    public void verifyAddress() {
        LoginPage   loginPage = new LoginPage(SingletonInstance.getInstance().getDriver());
        AccountPage accountPage = loginPage.openAccountPage(validUser);
        DefaultAddressPage defaultAddressPage = accountPage.openDefaultAddressPage();
        Address testAddress = defaultAddressPage.getRandomAddress();
        AddressBookPage  addressBookPage = defaultAddressPage.openAddressBookPage(testAddress);
        Address userAddress = addressBookPage.getAddress();
        assertEquals(userAddress, testAddress, "Invalid address");
    }
}
