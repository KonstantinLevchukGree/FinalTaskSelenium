package utils.singleton;

import lombok.SneakyThrows;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.property.PropertyUtil;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class SingletonInstance {
    private static SingletonInstance instance;
    private WebDriver driver;
    private final Properties testsData = PropertyUtil.getProperties("testsData.properties");
    private final Properties sauceLab = PropertyUtil.getProperties("sauceLab.properties");
    private final Properties chromeEnvironment = PropertyUtil.getProperties("chromeEnvironment.properties");
    private final Properties firefoxEnvironment = PropertyUtil.getProperties("firefoxEnvironment.properties");

    private SingletonInstance() {
    }

    public static SingletonInstance getInstance() {
        if (instance == null) {
            instance = new SingletonInstance();
        }
        return instance;
    }

    @SneakyThrows
    public WebDriver getDriver() {
        if (testsData.getProperty("environment").equals("local")) {
            driver = getBrowserDriver(testsData.getProperty("browser"));
            driver.manage().window().maximize();
        } else if (testsData.getProperty("environment").equals("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(testsData.getProperty("browser"));
            driver = new RemoteWebDriver(new URL(testsData.getProperty("hub.url")), capabilities);
        } else if (testsData.getProperty("environment").equals("remoteSauceLabs")) {
            authenticationSauceLab();
            driver = getRemoteWebDriverBrowser(testsData.getProperty("browser"));
        }
        return driver;
    }

    private WebDriver getBrowserDriver(String browser) {
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                FirefoxOptions options = new FirefoxOptions();
                driver = new FirefoxDriver(options);
                break;
        }
        return driver;
    }

    @SneakyThrows
    private WebDriver getRemoteWebDriverBrowser(String browser) {
        switch (browser) {
            case "chrome":
                driver = new RemoteWebDriver(new URL(sauceLab.getProperty("user.url")), setChromeOptions());
                break;
            case "firefox":
                driver = new RemoteWebDriver(new URL(sauceLab.getProperty("user.url")), setFirefoxOptions());
                break;
        }
        return driver;
    }

    private ChromeOptions setChromeOptions() {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName(chromeEnvironment.getProperty("platform"));
        browserOptions.setBrowserVersion(chromeEnvironment.getProperty("version"));
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("build", chromeEnvironment.getProperty("build"));
        sauceOptions.put("name", getTestAndClassName());
        browserOptions.setCapability("sauce:options", sauceOptions);
        return browserOptions;
    }

    private FirefoxOptions setFirefoxOptions() {
        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setPlatformName(firefoxEnvironment.getProperty("platform"));
        browserOptions.setBrowserVersion(firefoxEnvironment.getProperty("version"));
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("build", firefoxEnvironment.getProperty("build"));
        sauceOptions.put("name", getTestAndClassName());
        browserOptions.setCapability("sauce:options", sauceOptions);
        return browserOptions;
    }

    private String getTestAndClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String testName = null;
        for (StackTraceElement element : stackTrace
        ) {
            assert element.getFileName() != null;
            if (element.getFileName().replace(".java", "").trim().endsWith("Test")) {
                testName = "Test: " + element.getMethodName()
                        + "__Class: " + element.getFileName().replace(".java", "").trim()
                        + "__Package: " + element.getClassName().split(Pattern.quote("."))[0];
            }
        }
        return testName;
    }

    private void authenticationSauceLab() {
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", System.getenv(sauceLab.getProperty("user.name")));
        sauceOptions.setCapability("access_key", System.getenv(sauceLab.getProperty("access.key")));
    }

    public void quitAll() {
        driver.quit();
        driver = null;
    }
}
