package fr.dev.weather;

import okhttp3.*;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainFrame extends JFrame {

    public MainFrame(String title) {
        super(title);

        String apiKey = "dcac8953d6a57d38c6d818c7d86e2589";
        double latitude = 37.8267;
        double longitude = -122.4233;
        String forecastUrl = "https://api.darksky.net/forecast/" + apiKey + "/" + latitude + "," + longitude;

        System.out.println("Before request ...");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(forecastUrl).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println(response.body().string());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("ERROR : " + e.getMessage());
            }

        });

        System.out.println("After request ...");
    }

}
