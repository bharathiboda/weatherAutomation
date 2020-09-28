package com.weather.tests;
import com.weather.base.test.BaseTest;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.weather.pages.HomePage;

public class SuiteHomePageTests extends BaseTest {
    HomePage obj;

    @BeforeClass()
    public void initiateDriver(){
        driver("chrome");
        openUrl("https://weather.com/");
        obj = new HomePage();
    }

    @Test()
    public void testSearchLocation() throws InterruptedException {
        javascriptExecutor = (JavascriptExecutor) driver;
       // javascriptExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", driver.findElement(obj.search));
        String locationName = obj.checkLocation("Pune, Maharashtra, India");
        thisAssert.assertEquals(locationName, "Pune, Maharashtra, India Weather");
        thisAssert.assertAll();
    }

    @Test()
        public void testIsLogoClickable(){
        thisAssert.assertFalse(isLogoClickable(), "It is clickable but has to be rigid");
        thisAssert.assertAll();
        }


    private boolean isLogoClickable(){
        javascriptExecutor = (JavascriptExecutor)driver;
        boolean isClickable;
        try {
            javascriptExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", obj.isLogoClickable());
            isClickable = true;
        }
        catch (ElementClickInterceptedException e){
            isClickable = false;
        }
        return isClickable;
    }

    @AfterClass
    public void closeDriver(){
        super.closeDriver();
    }

}
