package com.weather.webDriver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Wd {
    public static Properties or;

    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    JavascriptExecutor javascriptExecutor;
    private static final long WAIT_TIME = 30;
    static {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/properties/browserDrivers/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/properties/browserDrivers/geckodriver.exe");
        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/src/main/properties/browserDrivers/IEDriverServer.exe");
    }

    public static String getProperties(String property){
        try {
            or = new Properties();
            FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/properties/ObjectResources/OR.properties");
            or.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return or.getProperty(property);
    }

    /**
     * This is to initialize driver object
     * @param browser - string ex: chrome or firefox
     * @param url- which url you want to call
     */
    public static void initializeDriver(String browser, String url) {
            switch (browser) {
                case "chrome":
                    threadDriver.set(new ChromeDriver());
                    //  driver = new ChromeDriver();
                    break;
                case "firefox":
                    threadDriver.set(new FirefoxDriver());
                    break;
                case "ie":
                    threadDriver.set(new InternetExplorerDriver());
                    break;
                default:
                    System.out.println("Please provide correct name of the browser : " + or.getProperty("browser"));
            }
        threadDriver.get().get(url);

    }

    /**
     * This method is to uses explicit wait to check the visibility of the element then performs the action
     * @param element - webelement
     * @return - boolean
     */
    public boolean waitForVisibility(WebElement element){
        boolean isDisplayed = true;
        try {
            WebDriverWait wait = new WebDriverWait(threadDriver.get(), WAIT_TIME);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (org.openqa.selenium.TimeoutException e) {
            isDisplayed = false;
        }
        return isDisplayed;
    }

    /**
     * This method uses explicit wait for the visibility of the list of elements.
     * if not catches the timeout exception
     * @param elements - List of web elements
     * @return boolean
     */
    public boolean waitForVisibility(List<WebElement> elements) {
        boolean isDisplayed = true;
        try {
            WebDriverWait wait = new WebDriverWait(threadDriver.get(), WAIT_TIME);
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (TimeoutException e) {
            isDisplayed = false;
        }
        return isDisplayed;
    }
    /**
     * This method excepts the web element uses explicit wait until element is clickable
     * @param element - webelement
     * @return boolean
     */
    private boolean isClickable(WebElement element) {
        boolean isClickable = true;
        try {
            WebDriverWait wait = new WebDriverWait(threadDriver.get(), WAIT_TIME);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            isClickable = false;
        }
        return isClickable;
    }

    /**
     * This method
     * @param by
     * @return
     */
    private boolean isDisplayed(By by) {
        boolean isDisplayed = true;
        try {
            WebDriverWait wait = new WebDriverWait(threadDriver.get(), WAIT_TIME);
            wait.until(ExpectedConditions.visibilityOf(threadDriver.get().findElement(by)));
        } catch (TimeoutException e) {
            isDisplayed = false;
        }
        return isDisplayed;
    }

    /**
     * This method uses JavaScript Executor to scroll to given x and y co ordinates
     * @param element - web element
     */
    protected void scrollToLocation(WebElement element){
        javascriptExecutor = (JavascriptExecutor) threadDriver.get();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);

    }
    /**
     * This method uses JavaScript Executor to scroll to given x and y co ordinates
     * @param x - x-co ordinate
     * @param y - y-co ordinate
     * @param element - web element
     */

    public void scrollToLocation(String x, String y, WebElement element) {
        javascriptExecutor = (JavascriptExecutor) threadDriver.get();
        waitForVisibility(element);
        javascriptExecutor.executeScript("window.scrollTo(" + x + "," + y + ");", element);
    }

    /**
     * This method uses web element and checks if the element is clickable if yes then clicks on it
     * if not it throws timeout exception from the isClickable method used in it
     * @param element - webelement
     */
    public void waitAndClick(WebElement element) {
        if(isClickable(element)) {
            element.click();
        }
    }

    /**
     * This method uses webelement and wait until visibility of it and then send keys into the locator
     * @param element - webelement
     * @param keys - string
     */
    public void waitAndSendKeys(WebElement element, String keys) {
        if (waitForVisibility(element)) {
            element.sendKeys(keys);
        }
    }

    /**
     * This method takes list of web elements and returns the text associated with all the web elements
     * @param elements - List<WebElement>
     * @return - List<String>
     */
    public List<String> getText(List<WebElement> elements) {
        waitForVisibility(elements);
        List<String> texts = new ArrayList<>();
        if(elements.size() > 0) {
            for (WebElement element : elements) {
                texts.add(element.getText());
            }
        } else {
            System.out.println("Size of the list is zero");
        }
        return texts;
    }
    /**
     * This method takes list of web elements and returns the text associated with all the web elements in the given range
     * @param elements - List<WebElement>
     * @return - List<String>
     */
    public List<String> getText(List<WebElement> elements, int StartIndex, int endIndex) {
        List<String> texts = new ArrayList<>();
        if(!waitForVisibility(elements)) {
            return texts;
        }
        if(elements.size() > 0) {
            waitForVisibility(elements);
            for (int i = StartIndex; i < endIndex; i++) {
                texts.add(elements.get(i).getText());
            }
        } else {
            System.out.println("Size of the list is zero");
        }
        return texts;
    }
    public List<String> getAttributeText(List<WebElement> elements, String attribute) {
        waitForVisibility(elements);
        List<String> texts = new ArrayList<>();
        if(elements.size() > 0) {
            for(WebElement element : elements) {
                texts.add(element.getAttribute(attribute));
            }
        } else {
            System.out.println("List is of zero size");
        }
        return texts;
    }

    /**
     * this method accepts
     * @param element
     * @param attribute
     * @return
     */
    public String getAttributeText(WebElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    /**
     * This takes web element and returns the text associated with it.
     * @param element
     * @return
     */
    public String getText(WebElement element) {
        String text = "";
        if(waitForVisibility(element)) {
            text = text + element.getText();
        }
        return text;
    }

    /**
     * This method if the element is not clickable in first attempt
     * @param element
     */
    public void retryClicking(WebElement element) {
        for(int i = 0; i < 3; i++) {
            waitAndClick(element);
        }
        throw new StaleElementReferenceException("element is not attached to the page");

    }
    public void waitTime(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }
    protected void takeAScreenShot() throws IOException {
        File file = ((TakesScreenshot)threadDriver.get()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "\\src\\main\\screenshots\\homePage.png"));
    }

}
