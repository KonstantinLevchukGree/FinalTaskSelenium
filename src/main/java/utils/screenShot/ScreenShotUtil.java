package utils.screenShot;

import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.singleton.SingletonInstance;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenShotUtil {

    @SneakyThrows
    public static void getSnapShot() {
        TakesScreenshot scrShot = ((TakesScreenshot) SingletonInstance.getInstance().getDriver());
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("src/test/java/screenshots/" + " "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss")) + ".png");
        FileUtils.copyFile(SrcFile, DestFile);
    }
    @Attachment
    public static byte[] attachScreenshot() {
        return ((TakesScreenshot) SingletonInstance.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
