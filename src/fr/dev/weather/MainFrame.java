package fr.dev.weather;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.JFrame;
import java.io.IOException;

public class MainFrame extends JFrame {

    public MainFrame(String title) {
        super(title);

        String apiKey = "dcac8953d6a57d38c6d818c7d86e2589";
        double latitude = 37.8267;
        double longitude = -122.4233;
        String forecastUrl = "https://api.darksky.net/forecast/" + apiKey + "/" + latitude + "," + longitude;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(forecastUrl).build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            System.err.println("ERROR : " + e);
        }

    }

}
