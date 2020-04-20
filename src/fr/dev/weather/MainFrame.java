package fr.dev.weather;

import fr.dev.weather.utilities.Alert;
import fr.dev.weather.utilities.Api;
import okhttp3.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

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
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
//                    System.out.println(response.body().string());
                    String jsonData = response.body().string();
                    JSONObject forecast = (JSONObject) JSONValue.parse(jsonData);
                    System.out.println(forecast.get("timezone"));
                    JSONObject currently = (JSONObject) forecast.get("currently");
                    System.out.println(currently.get("temperature"));
                } else {
                    Alert.error(MainFrame.this, "Oooops, an error was occured, try again please !");
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Alert.error(MainFrame.this, "Verify your internet connection please !");
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
