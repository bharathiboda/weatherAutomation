package com.weather.dataProviders;

import org.testng.annotations.DataProvider;

public class DpUtils {

    @DataProvider(name = "browsers")
    public static Object[][] dataProvider() {
        String[][] browsers = {{"chrome", "https://weather.com/"},
                { "firefox", "https://weather.com/"},
               /* {"ie", "https://weather.com/"}*/};
        return browsers;
    }
}
