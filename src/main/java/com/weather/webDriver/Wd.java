package com.weather.webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Wd{
    static Properties or;
    protected static WebDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/properties/browserDrivers/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/properties/browserDrivers/geckodriver.exe");
        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/src/main/properties/browserDrivers/IEDriverServer.exe");
    }

    private static void openProperties(){
        try {
            or = new Properties();
            FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/ObjectResources/OR.properties");
            or.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebDriver initializeDriver(String browser){
        WebDriver getDriver = null;

       switch (browser){
           case "chrome" :
               driver = new ChromeDriver();
               getDriver =  driver;
                break;
           case "firefox" :
               driver = new FirefoxDriver();
               getDriver = driver;
               break;
           case "ie" :
               driver = new InternetExplorerDriver();
               getDriver = driver;
               break;
           default:
               System.out.println("Please provide correct name of the browser : "+ or.getProperty("browser"));
       }

        return getDriver;
    }
    public static WebDriver initializeDriverUsingOR(){
        openProperties();
        return initializeDriver(or.getProperty("browser"));
    }

    public static void closeDriver(){
        driver.close();
    }

}
