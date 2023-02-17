package magentoTest;

import object.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import testResultUtil.TestResultExtension;
import utils.singleton.SingletonInstance;
@ExtendWith(TestResultExtension.class)

public class BaseTest {
    public static User validUser;
    public static User invalidUser;

    @BeforeEach
    public void initUser() {
        validUser = new User("valid");
        invalidUser = new User("invalid");
    }

    @AfterEach
    public void closeChrome() {
        SingletonInstance.getInstance().quitAll();
    }
}
