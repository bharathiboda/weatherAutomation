package com.weather.pages;

import com.weather.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;


public class HomePage extends BasePage {
    //look and feel locators
    private By search = By.id("LocationSearch_input");
    private By listOfLocations = By.xpath("//button[@id=\"LocationSearch_listbox-0\"]");
    private By nameOfLocation = By.cssSelector("h1[class*=\"CurrentConditions\"]");
    private By desiredLocation = By.cssSelector("button[class=\"_1r7mf r__KU _2yeqQ\"]");
    private By desiredLocationName = By.cssSelector("span[class=\"GkM1S\"]");
    private By negativeLocationName = By.cssSelector("div[class=\"_28jvQ\"]");
    private By weatherLogo = By.cssSelector("svg[name=\"the-weather-company\"]");
    private By weatherLogoIcon = By.cssSelector("svg[name=\"twc-logo\"]");
    private By menuId = By.id("MainMenuTrigger");

    //temp icons elements
    private By globe = By.cssSelector("div[data-testid=\"languageSelectorSection\"]");
    private By tempArrow = By.name("triangle-down");
    private By fahrenheit = By.cssSelector("span[class=\"_170nP\"]");
    private By celsius = By.xpath("//span[@class=\"_2X1ap\"]");
    private By tempDisplay = By.cssSelector("span[class=\"eg0E7\"]");
    private By socialMediaIcons = By.cssSelector("a[class=\"zzd7M\"]");

    //forecast menu items
    private By foreCastMenu = By.xpath("//a[@class=\"_1r7mf _2JY_Z _2yeqQ\"]");

    //functional test locators
    private By menuItems = By.cssSelector("span[class=\"_9LEtl\"]");
    private By menuIcon = By.id("MainMenuTrigger");

    //clicking on today locators
    private By todayForecast = By.xpath("//header[@class=\"_3A3B1\"]/h2[@class=\"_3et4e\"]");

    //clicking on hourly locators
    private By textHourly = By.cssSelector("h1[class=\"_3bC6K _2DTAD\"]");

    //clicking on 10 Day locators
    private By text10_Day = By.cssSelector("h1[class=\"_3bC6K _1IoG-\"]");

    //clicking on monthly locators
    private By textMonthly = By.cssSelector("div[class=\"locations-title monthly-page-title\"]").tagName("h1");

    //getting text from weekend locator
    private By textWeekend = By.cssSelector("div[classname=\"locations-title weekend-page-title\"]").tagName("h1");

    //getting text from radar page to check if radar tab is landed on desired page
    private By textRadar = By.cssSelector("div[classname=\"styles__Title__1hEzx\"]").tagName("h1");

    //locator for menu item weather
    private By menuWeatherTab = By.cssSelector("div[class=\"styles__navigationListItems__RAsa2\"]");

    //locator for continents display
    private By continentsList = By.cssSelector("div[class=\"_1z_mF\"]");

    /**
     * get method for social media icons
     * @return
     */
    public By getSocialMediaIcons() {
        return this.socialMediaIcons;
    }

    public By getSearch() {
        return this.search;
    }

    /**
     * this is to check if the search bar is taking address and landing on correct page
     *
     * @param location
     * @return
     * @throws InterruptedException
     */
    public String checkLocation(String location) {
        if(isAvailable(search)) {
            driver.findElement(search).click();
            driver.findElement(search).sendKeys(location);
        }
        if(isAvailable(listOfLocations)) {
            driver.findElement(listOfLocations).click();
        }
        return driver.findElement(nameOfLocation).getText();
    }

    /**
     * This is to check if the logo is clickable, directing to home page
     * @return
     */
    public boolean isLogoClickable() {
        driver.findElement(weatherLogo).click();
        return true;
    }

    /**
     * checks if the logo is displayed
     * @return - boolean, true if displayed otherwise false
     */
    public boolean logoVisibility() {
        //javascriptExecutor = (JavascriptExecutor)driver;
        scrollToLocation("130", "11", driver.findElement(weatherLogoIcon));
        return driver.findElement(weatherLogoIcon).isDisplayed();
    }

    /**
     * checks if menu is displayed
     * @return - boolean, true if displayed otherwise false.
     */
    public boolean menuLocation() {
        javascriptExecutor = (JavascriptExecutor)driver;
        //scrollToLocation();
        return driver.findElement(menuId).isDisplayed();
    }

    /**
     * checks if the temperature and globe icon is displayed
     * @return - boolean, true if displayed otherwise false
     */
    public boolean globeAndTemperatureDisplay() {
        return driver.findElement(globe).isDisplayed();
    }

    /**
     * checks if the search bar is displayed
     * @return - boolean true if displayed otherwise false
     */
    public boolean isSearchBarPresent() {
        return driver.findElement(search).isDisplayed();
    }


    /**
     * it collects all the forecast items
     * @return - list of strings of forecast items
     */
    public List<String> areForaCastMenuItemsDisplay() {
        List<WebElement> items = driver.findElements(foreCastMenu);
        List<String> namesOFMenu = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            namesOFMenu.add(items.get(i).getText());
        }
        return namesOFMenu;
    }

    /**
     * checks what is the default text displayed in the search bar
     * @return - string present in the search bar in the default position
     */
    public String isDefaultTextDisplayed() {
        return driver.findElement(search).getAttribute("aria-label");
    }

    /**
     * gets all the social media names displayed in the footer
     * @return- returns the list of strings of social media names displayed.
     */
    public List<String> isSocialMediaIconDisplayed() {
        List<WebElement> listOfIcons = driver.findElements(socialMediaIcons);
        List<String> icons = new ArrayList<>();
        for (WebElement icon : listOfIcons) {
            icons.add(icon.getAttribute("data-testid"));
        }
        return icons;
    }

    //functional test cases of home page starts here

    /**
     * clicks on homepage logo
     */
    public void isClickingOnLogoReturnsHomePage() {
        driver.findElement(weatherLogoIcon).click();
    }

    /**
     * enter desired location in search bar and check if it is directing to desired page
     * @param location
     * @return - name of the location
     */
    public String isLandingOnDesiredLocationPositive(String location) {
        if(isAvailable(search))
          driver.findElement(search).click();
        driver.findElement(search).sendKeys(location);
        driver.findElements(desiredLocation).get(0).click();
        return driver.findElements(desiredLocationName).get(0).getText();
    }

    /**
     * negative test for searching desired location
     *
     * @param location - string
     * @return string of the location
     */
    public String isLandingOnDesiredLocationNegative(String location) {
        String text = "";
        scrollToLocation("323", "22", driver.findElement(search));
        new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(search)).sendKeys(location);
        // driver.findElement(search).sendKeys(location);
        text = driver.findElement(negativeLocationName).getText();

        return text;
    }

    /**
     * This is to check if the different countries temperature options are displayed
     */
    public String isFahrenheitSelected() {
        String displayed = "";
        driver.findElement(tempArrow).click();
        if (isAvailable( fahrenheit))
            driver.findElement(fahrenheit).click();
        if (isAvailable( fahrenheit)) {
            displayed = driver.findElement(tempDisplay).getText();
        }
        return displayed;
    }

    /**
     * this is to check if the celsius is selecting or not
     * @return string of temperature
     */
    public String isCelsiusSelected() {
        String displayed = "";
        scrollToLocation("1294", "34", driver.findElement(tempArrow));
        driver.findElement(tempArrow).click();
        if (isAvailable( celsius)) {
            driver.findElement(celsius).click();
            displayed = driver.findElement(tempDisplay).getText();
        }
        return displayed;
    }

    /**
     * This method gets inner headings of main menu as a list
     * @return - list of menu items (headings)
     */

    public List<String> menuItemsDisplayed() {
        if(isAvailable(menuIcon))
          driver.findElement(menuIcon).click();
        List<WebElement> menu = new ArrayList<>();
        if (isAvailable( menuItems)) {
            menu = driver.findElements(menuItems);
        }
        List<String> menuList = new ArrayList<>();
        for (WebElement item : menu) {
            menuList.add(item.getText());
        }
        return menuList;
    }

    /**
     * This method checks if the main forecast tabs are clickable and gets the main weather display of the page
     * @param index
     * @param foreCastName
     * @return
     */

    public String selectForecastTabAndVerifyText(int index, String foreCastName) {
        if(isAvailable(By.cssSelector("a[data-from-string=\"localsuiteNav_" + index + "_" + foreCastName + "\"]"))) {
            driver.findElement(By.cssSelector("a[data-from-string=\"localsuiteNav_" + index + "_" + foreCastName + "\"]")).click();
        }
            if (foreCastName.equals("Today"))
                return todayForecastDisplay();
            if (foreCastName.equals("Hourly"))
                return hourlyForecastDisplay();
            if (foreCastName.equals("10 Day"))
                return tenDayForecastDisplay();
            if (foreCastName.equals("Monthly"))
                return monthlyForecastDisplay();
            if (foreCastName.equals("Weekend"))
                return weekendForecastDisplay();
            if (foreCastName.equals("Radar"))
                return radarForecastDisplay();
        else
            return "provide correct locator " + foreCastName + " is wrong, Note: forecast is case sensitive";
    }

    /**
     * This method used in isForecastTabsClickableAndVerifyText to get text from the today page
     * @return string of current location today weather
     */
    private String todayForecastDisplay() {
        String text = "";
        if(isAvailable(todayForecast))
        text = driver.findElement(todayForecast).getText();
        return text;
    }

    /**
     * This method used in isForecastTabsClickableAndVerifyText to get text from the hourly page
     * @return String of the current location hourly weather
     */
    private String hourlyForecastDisplay() {
        String text = "";
        if(isAvailable(textHourly))
            text = driver.findElement(textHourly).getText();
        return text;
    }

    /**
     * This method is used in isForecastTabsClickableAndVerifyText to get text from the 10_Day page
     * @return  string of current page and 10_day weather display
     */
    private String tenDayForecastDisplay() {
        String text = "";
        if(isAvailable(text10_Day))
        text = driver.findElement(text10_Day).getText();
        return text;
    }

    /**
     * This method is used in isForecastTabsClickableAndVerifyText to get text from the monthly page
     * @return String of current location and monthly display
     */
    private String monthlyForecastDisplay() {
        String text = "";
        if(isAvailable(textMonthly))
        text = driver.findElement(textMonthly).getText();
        return text;
    }

    /**
     * This method is used in isForecastTabsClickableAndVerifyText to get text from the weekend page
     * @return String of current location and weekend display
     */
    private String weekendForecastDisplay() {
        String text = "";
        if(isAvailable(textWeekend))
         text = driver.findElement(textWeekend).getText();
        return text;
    }
    /**
     * This method is used in isForecastTabsClickableAndVerifyText to get text from the radar page
     * @return String of current location and radar display
     */
    private String radarForecastDisplay(){
        String text ="";
        if(isAvailable(textRadar))
         text = driver.findElement(textRadar).getText();
        return text;
    }

    /**
     * This method to get the menu weather tab list of items
     * @return - list of strings of weather tab list.
     */
    public List<String> selectMenuWeatherTabListItemAndVerifyText() {
        driver.findElement(menuIcon).click();
        List<String> list = new ArrayList<>();
        if(isAvailable(menuWeatherTab)) {
           List<WebElement> listOfItems = driver.findElements(menuWeatherTab);
           for(WebElement item : listOfItems){
               list.add(item.getText());
           }
        }
        return list;
    }
    /**
     *
     */
    public List<String> displayContinents() {
        List<String> continents = new ArrayList<>();
        if(isAvailable(tempArrow))
           driver.findElement(tempArrow).click();
        if(isAvailable(continentsList)){
           List<WebElement> wcontinents = driver.findElements(continentsList);
           for(WebElement continent : wcontinents) {
               continents.add(continent.getText());
           }
        }
       return continents;
    }
  public String wind() { driver.findElement(By.cssSelector("a[data-from-string=\"localsuiteNav_2_Hourly\"]")).click();
       List<WebElement> elements = driver.findElements(By.cssSelector("div[data-testid=\"DetailsSummary\"]"));
       String element = elements.get(0).findElement(By.xpath("//div[@data-testid=\"wind\"]")).getText();
       return element;
  }

}

