package com.weather.pages;

import com.weather.base.page.BasePageInit;
import com.weather.webDriver.Wd;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;


public class HomePageInit extends Wd {

    public HomePageInit() {
        PageFactory.initElements(threadDriver.get(), this);
    }

    //look and feel
    @FindBy(id = "LocationSearch_input")
    private WebElement search;

    private By bySearch = By.id("LocationSearch_input");

    @FindBy(xpath = "//button[@id=\"LocationSearch_listbox-0\"]")
    private WebElement listOfLocations;

    @FindBy(css = "h1[class*=\"CurrentConditions\"]")
    private WebElement nameOfLocation;

    @FindBy(css = "button[class=\"_1r7mf r__KU _2yeqQ\"]")
    private List<WebElement> listDesiredLocation;

    @FindBy(xpath = "//button[@id=\"LocationSearch_listbox-0\"]")
    private WebElement desiredLocation;

    @FindBy(xpath = "//div[@class=\"CurrentConditions--header--3-4zi\"]/h1[@class=\"CurrentConditions--location--1Ayv3\"]")
    private WebElement desiredLocationName;

    @FindBy(xpath = "//div[@class=\"SearchResults--NoResults--28jvQ\"]")
    private WebElement negativeLocationName;

    @FindBy(css ="svg[name=\"the-weather-company\"]")
    private WebElement weatherLogo;

    @FindBy(css = "svg[name=\"twc-logo\"]")
    private WebElement weatherLogoIcon;

    @FindBy(css = "svg[name=\"twc-logo\"]")
    private WebElement weatherLogoPresent;

    @FindBy(id = "MainMenuTrigger")
    private WebElement menuId;

    //temp icon elements
    @FindBy(css = "svg[name=\"globe\"]")
    private WebElement globe;

    @FindBy(css = "svg[class=\"Icon--icon--2AbGu Icon--lightTheme--3RdOJ LanguageSelector--arrowIcon--AQWbj\"]")
    private WebElement tempArrow;

    @FindBy(xpath="//span[@class=\"UnitSelector--UnitSelectorButtonTextF--170nP\"]")
    private WebElement fahrenheit;

    @FindBy(xpath = "//span[@class=\"UnitSelector--UnitSelectorButtonTextC--2X1ap\"]")
    private WebElement celsius;

    @FindBy(className = "LanguageSelector--unitDisplay--eg0E7")
    private WebElement tempDisplay;

    @FindBy(css = "a[class=\"LinkList--Link--zzd7M\"]")
    private List<WebElement> socialMediaIcons;

    private By bySocialMediaIcons = By.cssSelector("a[class=\"LinkList--Link--zzd7M\"]");

    //forecast menu items
    @FindBy(xpath = "//span[@class=\"styles--liContent--1nCd7\"]")
    private List<WebElement> foreCastMenu;

    //functional test locators
    @FindBy(css = "span[class=\"SiteNavigationLinks--title--9LEtl\"]")
    private List<WebElement> menuItems;

    @FindBy(id = "MainMenuTrigger")
    private WebElement menuIcon;

    //clicking on today locators
    @FindBy(xpath = "//div[@id=\"WxuTodayWeatherCard-main-486ce56c-74e0-4152-bd76-7aea8e98520a\"]//header[@class=\"Card--cardHeader--3A3B1\"]")
    private WebElement todayForecast;

    //clicking on hourly locators
    @FindBy(css = "h1[class=\"LocationPageTitle--PageHeader--3bC6K HourlyForecast--CardHeader--2DTAD\"]")
    private WebElement textHourly;

    //clicking on 10 Day locators
    @FindBy(css = "h1[class*=\"PageHeader--3bC6K DailyForecast--CardHeader--1IoG-\"]")
    private WebElement text10_Day;

    //clicking on monthly locators
    @FindBy(xpath = "//div[@id=\"main-PageTitle-4337b557-ccef-42aa-af8c-0b0793d89c82\"]")
    private WebElement textMonthly;


    //getting text from weekend locator
    @FindBy(css = "section[data-testid=\"WeekendForecast\"]")
    private WebElement textWeekEnd;

    @FindBy(css = "a[class=\"_1r7mf _2JY_Z _2M5ZG _2yeqQ\"]")
    private WebElement weekendTab;

    //getting text from radar page to check if radar tab is landed on desired page
    @FindBy(xpath = "//div[@classname=\"styles__Title__1hEzx\"]/h1")
    private WebElement textRadar;

    //locator for menu item weather
    @FindBy(css = "div[class=\"styles__navigationListItems__RAsa2\"]")
    private List<WebElement> menuWeatherTab;

    //locator for continents display
    @FindBy(css = "div[class=\"Disclosure--SummaryDefault--1z_mF\"]")
    private List<WebElement> continentsList;

    //locator for wind and hourly tabs
    @FindBy(xpath = "//a[@data-from-string=\"localsuiteNav_2_Hourly\"]")
    private WebElement hourly;

    @FindBy(xpath = "//span[@class=\"Wind--windWrapper--1Va1P DetailsTable--value--1F3Ze\"]")
    private WebElement wind;

    @FindBy(css = "svg[name=\"close\"]")
    private WebElement privacyClose;

    @FindBy(css = "ul[class=\"LinkList--LinkList--N5p7e Footer--linklist--1nSN2 Footer--socialLinks--3XxFx LinkList--inline-no-border--15tLI\"]")
    private WebElement socialIconsHeader;

    public By getBySocialMediaIcons() {
        return this.bySocialMediaIcons;
    }


    /**
     * checks if the logo is displayed
     * @return - boolean, true if displayed otherwise false
     */
    public boolean logoVisibility() {
        boolean isDisplayed = false;
        if(waitForVisibility(weatherLogoPresent)) {
            scrollToLocation("130", "11", weatherLogoIcon);
            waitForVisibility(weatherLogoIcon);
            isDisplayed = weatherLogoIcon.isDisplayed();
        }
        return isDisplayed;
    }

    /**
     * checks if menu is displayed
     * @return - boolean, true if displayed otherwise false.
     */
    public boolean menuLocation() {
        //scrollToLocation();
        return menuId.isDisplayed();
    }

    /**
     * checks if the temperature and globe icon is displayed
     * @return - boolean, true if displayed otherwise false
     */
    public boolean globeAndTemperatureDisplay() {
        waitForVisibility(globe);
        return globe.isDisplayed();
    }

    /**
     * checks if the search bar is displayed
     * @return - boolean true if displayed otherwise false
     */
    public boolean isSearchBarPresent() {
        return search.isDisplayed();
    }


    /**
     * it collects all the forecast items
     * @return - list of strings of forecast items
     */
    public List<String> areForaCastMenuItemsDisplay() {
        waitAndClick(privacyClose);
        return getText(foreCastMenu, 0, 6);
    }

    /**
     * checks what is the default text displayed in the search bar
     * @return - string present in the search bar in the default position
     */
    public String isDefaultTextDisplayed() {
        waitForVisibility(search);
        return search.getAttribute("aria-label");
    }

    /**
     * gets all the social media names displayed in the footer
     * @return- returns the list of strings of social media names displayed.
     */
    public List<String> isSocialMediaIconDisplayed() {
        waitAndClick(privacyClose);
        scrollToLocation("341", "3008", socialIconsHeader);
        return getAttributeText(socialMediaIcons, "data-testid");
    }

    /**
     * clicks on homepage logo
     */
    public void isClickingOnLogoReturnsHomePage() {
        waitAndClick(weatherLogoIcon);
        //weatherLogoIcon.click();
    }

    /**
     * enter desired location in search bar and check if it is directing to desired page
     * @param location
     * @return - name of the location
     */
    public String isLandingOnDesiredLocationPositive(String location){
        PageFactory.initElements(threadDriver.get(), this);
            waitForVisibility(privacyClose);
            privacyClose.click();
            // waitAndClick(privacyClose);
            scrollToLocation("335", "22", search);
            waitTime(1000);
            waitAndClick(search);
            search.sendKeys(location);
            waitForVisibility(desiredLocation);
            desiredLocation.click();
            //waitForVisibility(desiredLocationName);
            return getText(desiredLocationName);
    }

    //we should have one method accepts parameter webelement
    //if it says click it should wait and click

    /**
     * negative test for searching desired location
     * @param location - string
     * @return string of the location
     */
    public boolean getTextLandingOnDesiredLocationNegative(String location) {
        PageFactory.initElements(threadDriver.get(), this);
        waitAndClick(privacyClose);
        scrollToLocation("335", "22", search);
        waitTime(1000);
        search.click();
        waitAndSendKeys(search, location);
        waitTime(2000);
        search.click();
        //waitAndSendKeys(search, location);
        String negResult = getText(negativeLocationName);
        if(negResult.contains("No results found") || negResult.contains("You have no recent locations"))
            return true;
        else
            return false;
    }

    /**
     * This is to check if the different countries temperature options are displayed
     */
    public List<String> getTextFahrenheitOrCelsiusSelected() {
        waitAndClick(privacyClose);
        List<WebElement> tempMeasure = new ArrayList<>();
        tempMeasure.add(fahrenheit);
        tempMeasure.add(celsius);
        List<String> tempList = new ArrayList<>();
        for(WebElement temp : tempMeasure) {
            scrollToLocation("916", "25", tempArrow);
            tempArrow.click();
            //waitAndClick(tempArrow);
            waitAndClick(temp);
            scrollToLocation("799", "20", globe);
            waitForVisibility(tempDisplay);
            tempList.add(tempDisplay.getText());
            waitTime(2000);
        }
        return tempList;
    }


    /**
     * This method gets inner headings of main menu as a list
     * @return - list of menu items (headings)
     */

    public List<String> menuItemsDisplayed() {
        waitAndClick(menuIcon);
       return getText(menuItems);
    }

    /**
     * This method checks if the main forecast tabs are clickable and gets the main weather display of the page
     * @param index
     * @param foreCastName
     * @return
     */

    public boolean selectForecastTabAndVerifyText(int index, String foreCastName) {
        boolean isThere = false;
       waitAndClick(privacyClose);
       // scrollToLocation("15", "122", foreCast);
       WebElement foreCast = threadDriver.get().findElement(By.cssSelector("a[data-from-string=\"localsuiteNav_" + index + "_" + foreCastName + "\"]"));
       waitForVisibility(foreCast);
       foreCast.click();
        if (foreCastName.equals("Today")) {
            return getText(todayForecast).contains("Today's");
        }
        if (foreCastName.equals("Hourly")) {
            return getText(textHourly).contains("Hourly Weather");
        }
        if (foreCastName.equals("10 Day")) {
            return getText(text10_Day).contains("10 Day Weathe");
        }
        if (foreCastName.equals("Monthly")) {
            return getText(textMonthly).contains("Monthly Weather");
        }
        if (foreCastName.equals("Weekend")) {
            return getAttributeText(textWeekEnd, "aria-label").contains("Weekend Weather");
        }
        if (foreCastName.equals("Radar")) {
            return getText(textRadar).contains("Radar");
        }
       return isThere;
    }

    /**
     * This method to get the menu weather tab list of items
     * @return - list of strings of weather tab list.
     */
    public List<String> selectMenuWeatherTabListItemAndVerifyText() {
        menuIcon.click();
       return getText(menuWeatherTab);
    }
    /**
     *
     */
    public List<String> displayContinents() {
        PageFactory.initElements(threadDriver.get(), this);
        waitAndClick(privacyClose);
        waitAndClick(tempArrow);
        return getText(continentsList);
    }
    public String changeInUnitsIfTemperatureChangesFahrenheit() {
        waitAndClick(privacyClose);
        scrollToLocation("914", "35", tempArrow);
        waitForVisibility(tempArrow);
        tempArrow.click();
        waitAndClick(fahrenheit);
        waitAndClick(hourly);
        return getText(wind);
    }
    public String changeInWindUnitIfTemperatureChangesCelsius() {
        waitAndClick(privacyClose);
        scrollToLocation("914", "35", tempArrow);
       // waitAndClick(weatherLogoIcon);
        waitAndClick(tempArrow);
        waitForVisibility(celsius);
        celsius.click();
        waitForVisibility(hourly);
        hourly.click();
        return getText(wind);
    }
}

