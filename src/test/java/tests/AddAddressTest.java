package tests;

import magentoPage.AccountPage;
import magentoPage.AddressBookPage;
import magentoPage.DefaultAddressPage;
import magentoPage.LoginPage;
import object.address.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddAddressTest extends BaseTest {
    @Test
    public void verifyAddress() {
        LoginPage loginPage = new LoginPage();
        AccountPage accountPage = loginPage.openAccountPage(validUser);
        DefaultAddressPage defaultAddressPage = accountPage.openDefaultAddressPage();
        Address testAddress = defaultAddressPage.createRandomAddress();
        AddressBookPage addressBookPage = defaultAddressPage.openAddressBookPage(testAddress);
        Address userAddress = addressBookPage.getAddress();
        assertEquals(userAddress, testAddress, "Invalid address");
    }
}
