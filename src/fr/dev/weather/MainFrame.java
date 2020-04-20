package fr.dev.weather;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        new SwingWorker<String, Void>() {

            @Override
            protected String doInBackground() throws Exception {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(forecastUrl).build();
                Call call = client.newCall(request);
                try {
                    Response response = call.execute();
                    return response.body().string();
                } catch (IOException e) {
                    System.err.println("ERROR : " + e);
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    System.out.println(get()); // get() method return the return of doInBackGround methed
                } catch (InterruptedException | ExecutionException e) {
                    System.err.println("ERROR : " + e);
                }
            }

        }.execute();
        System.out.println("After request ...");
    }

}
