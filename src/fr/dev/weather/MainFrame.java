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

    private CurrentWeather currentWeather;

    private JLabel locationLabel;
    private JLabel timeLabel;
    private JLabel temperatureLabel;

    public MainFrame(String title) {
        super(title);

        Container contentPane = getContentPane();
        setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        contentPane.setBackground(Color.decode("#8EA2C6"));


        locationLabel = new JLabel("Paris, FR", SwingConstants.CENTER);
        locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        locationLabel.setForeground(Color.WHITE);
        locationLabel.setFont(new Font("San Francisco", Font.PLAIN, 24));

        timeLabel = new JLabel("Time is 08:11 and temperature is :", SwingConstants.CENTER);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeLabel.setForeground(new Color(255, 255, 255, 128));
        timeLabel.setFont(new Font("San Francisco", Font.PLAIN, 18));

        temperatureLabel = new JLabel("<html>100&deg;</html>", SwingConstants.CENTER);
        temperatureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        temperatureLabel.setForeground(Color.WHITE);
        temperatureLabel.setFont(new Font("San Francisco", Font.PLAIN, 160));

        add(locationLabel);
        add(timeLabel);
        add(temperatureLabel);

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

                        currentWeather = getCurrentWeatherDetails(jsonData);

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

    private CurrentWeather getCurrentWeatherDetails(String jsonData) throws ParseException {
        CurrentWeather currentWeather = new CurrentWeather();
        JSONObject forecast = (JSONObject) JSONValue.parseWithException(jsonData);
        JSONObject currently = (JSONObject) forecast.get("currently");
        currentWeather.setTimezone((String) forecast.get("timezone"));
        currentWeather.setTime((long) currently.get("time"));
        currentWeather.setTemperature(Double.parseDouble(currently.get("temperature") + ""));
        currentWeather.setHumidity(Double.parseDouble(currently.get("humidity") + ""));
        currentWeather.setPrecipProbability(Double.parseDouble(currently.get("precipProbability") + ""));
        currentWeather.setSummary((String) currently.get("summary"));
        return currentWeather;
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
