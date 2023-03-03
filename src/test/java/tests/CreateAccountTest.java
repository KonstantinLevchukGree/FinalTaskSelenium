package tests;

import magentoPage.AccountPage;
import magentoPage.LoginPage;
import object.user.User;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriverException;

import static org.junit.jupiter.api.Assertions.*;

public class CreateAccountTest extends BaseTest {

    @Test
    public void verifyCreateValidAccount() {
        LoginPage loginPage = new LoginPage();
        AccountPage accountPage = loginPage.openAccountPage(validUser);
        User user = accountPage.getRegisteredUser(validUser.getFirstName());
        assertEquals(user, validUser, "Account not created");
    }

    @Test
    public void verifyCreateInvalidAccount() {
        LoginPage loginPage = new LoginPage();
        AccountPage accountPage = loginPage.openAccountPage(invalidUser);
        Throwable thrown = assertThrows(WebDriverException.class, () -> accountPage.getRegisteredUser(invalidUser.getFirstName()));
        assertNotNull(thrown.getMessage(), "Account created");
    }
}
