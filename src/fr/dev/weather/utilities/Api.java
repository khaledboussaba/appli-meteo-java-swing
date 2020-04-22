package fr.dev.weather.utilities;

public class Api {

    private static final String FORECAST_API_BASIC_URL = "https://api.darksky.net/forecast/";
    private static final String FORECAST_API_KEY = "dcac8953d6a57d38c6d818c7d86e2589";

    public static String getForecastUrl(double latitude, double longitude) {
        return FORECAST_API_BASIC_URL + FORECAST_API_KEY + "/" + latitude + "," + longitude + "?units=si"; // ?units=si to get temperature in celsius
    }

}
