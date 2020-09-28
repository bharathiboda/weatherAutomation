package com.weather.commons;

public enum WeatherForecast {
    TODAY("Today", "Today's Forecast for East Windsor Township, NJ", "Today forecast is not displayed as expected"),
    HOURLY("Hourly", "Hourly Weather-East Windsor Township, NJ", "Hourly forecast is not displayed as expected"),
    TENDAY("10 Day", "10 Day Weather-East Windsor Township, NJ", "10_Day forecast is not displayed as expected"),
    WEEKEND("Weekend", "East Windsor Township, NJ Weekend Weather", "Weekend forecast is not displayed as expected"),
    MONTHLY("Monthly", "East Windsor Township, NJ Monthly Weather", "Monthly forecast is not displayed as expected"),
    RADAR("Radar", "Radar and Weather Maps for East Windsor Township, NJ", "Radar forecast is not displayed as expected");

    private String foreCast;
    private String expectedValue;
    private String actualValue;

    private WeatherForecast(String foreCast, String expectedValue, String actualValue) {
        this.foreCast = foreCast;
        this.expectedValue = expectedValue;
        this.actualValue = actualValue;
    }
    public String getForeCast() {
        return foreCast;
    }
    public String getExpectedValue() {
        return expectedValue;
    }
    public String getActualValue() {
        return actualValue;
    }

}

