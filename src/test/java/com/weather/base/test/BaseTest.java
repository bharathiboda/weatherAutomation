package com.weather.base.test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.asserts.SoftAssert;
import com.weather.webDriver.Wd;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver = null;
    protected SoftAssert thisAssert;
    protected JavascriptExecutor javascriptExecutor;


    protected void driver(String browser) {
        this.driver = Wd.initializeDriver(browser);
        //this.driver = Wd.initializeDriverUsingOR();
    }
    protected void openUrl(String url){
        driver.get(url);
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        thisAssert = new SoftAssert();

    }

    protected void scrollToLocation(String x, String y, WebElement element){
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy("+ x +","+ y +");", element);

    }
    protected void takeAScreenShot() throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "\\src\\main\\screenshots\\homePage.png"));
    }

    protected void closeDriver(){
        Wd.closeDriver();
    }
}
