package tests;

import object.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import testResultUtil.TestResultExtension;
import utils.singleton.SingletonDriver;

@ExtendWith(TestResultExtension.class)

public class BaseTest {
    User validUser;
    User invalidUser;

    @BeforeEach
    public void init() {
        validUser = new User().fillUser("valid");
        invalidUser = new User().fillUser("invalid");
    }

    @AfterAll
    public static void quitDriver() {
        SingletonDriver.quitAll();
    }
}
