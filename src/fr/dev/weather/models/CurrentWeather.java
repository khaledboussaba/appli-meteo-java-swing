package fr.dev.weather.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CurrentWeather {

    private String timezone;
    private long time;
    private double temperature;
    private double humidity;
    private double precipProbability;
    private String summary;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public long getTime() {
        return time;
    }

    public String getFormattedTime() {
        Date date = new Date(getTime() * 1000L);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimezone()));
        return formatter.format(date);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getTemperature() {
        return (int)Math.round(temperature);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getPrecipProbability() {
        return (int)Math.round(precipProbability);
    }

    public void setPrecipProbability(double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCity() {
        return getTimezone().split("/")[1];
    }
}
