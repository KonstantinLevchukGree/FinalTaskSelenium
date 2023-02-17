package testResultUtil;

import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import utils.screenShot.ScreenShotUtil;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestResultExtension implements AfterTestExecutionCallback {
    @SneakyThrows
    @Override
    public void afterTestExecution(ExtensionContext context) {
        boolean passed = context.getExecutionException().isPresent();
        String result = passed ? "FAILED" : "PASSED";
        if (result.equals("FAILED")) {
            ScreenShotUtil.attachScreenshot();
            Path content = Paths.get("src/test/resources/environment.properties");
            try (InputStream is = Files.newInputStream(content)) {
                Allure.addAttachment("Environment", is);
            }
        }
    }
}
