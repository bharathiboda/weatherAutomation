package com.weather.webDriver;

import com.weather.webDriver.Wd;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage extends Wd {

    //wait until method here
    //if element is not there , customized methods'
    //common requirements for all the pages
    // this class will be extended by test class of each page

    private static final int WAIT_TIME = 25;
    private ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
    protected JavascriptExecutor javascriptExecutor;
    public BasePage(){

    }
    public BasePage(WebDriver driver) {
        //super(driver);
        threadLocal.set(driver);
    }

    public boolean isAvailable(By by){
        boolean isDisplayed = true;
        try {
            WebDriverWait wait = new WebDriverWait(threadLocal.get(), WAIT_TIME);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (org.openqa.selenium.TimeoutException e) {
            isDisplayed = false;
        }
        return isDisplayed;
    }
    public boolean isClickable(WebElement element) {
        boolean isClickable = true;
        try {
            WebDriverWait wait = new WebDriverWait(threadLocal.get(), WAIT_TIME);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            isClickable = false;
        }
        return isClickable;
    }
    public boolean isDisplayed(By by) {
        boolean isDisplayed = true;
        try {
            WebDriverWait wait = new WebDriverWait(threadLocal.get(), WAIT_TIME);
            wait.until(ExpectedConditions.visibilityOf(threadLocal.get().findElement(by)));
        } catch (TimeoutException e) {
            isDisplayed = false;
        }
        return isDisplayed;
    }
    public void scrollToLocation(String x, String y, WebElement element){
        javascriptExecutor = (JavascriptExecutor) threadLocal.get();
        javascriptExecutor.executeScript("window.scrollBy("+ x +","+ y +");", element);

    }

    public void click(WebElement element) {
        if(isClickable(element))
            element.click();
    }
    public void waitTime(long time) {
        try {
            this.wait(time);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }


    }

