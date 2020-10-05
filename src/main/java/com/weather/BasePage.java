package com.weather;

import com.weather.webDriver.Wd;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage extends Wd {
    //wait until method here
    //if element is not there , customized methods'
    //common requirements for all the pages
    // this class will be extended by test class of each page
    private static final int WAIT_TIME = 180;
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
    public boolean isClickable(By by) {
        boolean isClickable = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (TimeoutException e) {
            isClickable = false;
        }
        return isClickable;
    }
    protected void scrollToLocation(String x, String y, WebElement element){
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy("+ x +","+ y +");", element);

    }
}
