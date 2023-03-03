package utils.webDriverWait;

import org.openqa.selenium.support.ui.WebDriverWait;
import utils.property.PropertyUtil;
import utils.singleton.SingletonDriver;

import java.time.Duration;
import java.util.Properties;

public class ExplicitWait {
    private static final Properties testData = PropertyUtil.getProperties("testsData.properties");

    public static WebDriverWait getExplicitWait() {
        return new WebDriverWait(SingletonDriver.getInstance()
                , Duration.ofSeconds(Integer.parseInt(testData.getProperty("explicit.time"))));
    }
}
