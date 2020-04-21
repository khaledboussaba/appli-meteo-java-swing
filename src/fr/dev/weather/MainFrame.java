package fr.dev.weather;

import fr.dev.weather.models.CurrentWeather;
import fr.dev.weather.utilities.Alert;
import fr.dev.weather.utilities.Api;
import okhttp3.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private static final String GENERIC_ERROR_MESSAGE = "Oooops, an error was occured, try again please !";
    private static final String INTERNET_CONNECTIVITY_ERROR_MESSAGE = "Verify your internet connection please !";

    public MainFrame(String title) {
        super(title);

        double latitude = 37.8267;
        double longitude = -122.4233;
        String forecastUrl = Api.getForecastUrl(latitude, longitude);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(forecastUrl).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                try (ResponseBody body = response.body()) {
                    if (response.isSuccessful()) {
                        String jsonData = body.string();

                        JSONObject forecast = (JSONObject) JSONValue.parseWithException(jsonData);
                        JSONObject currently = (JSONObject) forecast.get("currently");

                        CurrentWeather currentWeather = new CurrentWeather();
                        currentWeather.setTimezone((String) forecast.get("timezone"));
                        currentWeather.setTime((long) currently.get("time"));
                        currentWeather.setTemperature(Double.parseDouble(currently.get("temperature") + ""));
                        currentWeather.setHumidity(Double.parseDouble(currently.get("humidity") + ""));
                        currentWeather.setPrecipProbability(Double.parseDouble(currently.get("precipProbability") + ""));
                        currentWeather.setSummary((String) currently.get("summary"));

                        System.out.println(currentWeather.getFormattedTime());

                    } else {
                        Alert.error(MainFrame.this, GENERIC_ERROR_MESSAGE);
                    }
                } catch (ParseException | IOException e) {
                    Alert.error(MainFrame.this, GENERIC_ERROR_MESSAGE);
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Alert.error(MainFrame.this, INTERNET_CONNECTIVITY_ERROR_MESSAGE);
            }

        });

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

}
