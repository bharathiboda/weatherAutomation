package com.weather;

import com.weather.webDriver.Wd;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage extends Wd {
    //wait until method here
    //if element is not there , customized methods'
    //common requirements for all the pages
    // this class will be extended by test class of each page
    private int WAIT_TIME = 1000;
    protected JavascriptExecutor javascriptExecutor;

    public boolean isAvailable(By by){
        boolean isDisplayed = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (org.openqa.selenium.TimeoutException e) {
            isDisplayed = false;
        }
        return isDisplayed;
    }
    protected void scrollToLocation(String x, String y, WebElement element){
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy("+ x +","+ y +");", element);

    }
}
