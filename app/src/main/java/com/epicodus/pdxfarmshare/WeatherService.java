package com.epicodus.pdxfarmshare;

import android.util.Log;

import com.epicodus.pdxfarmshare.models.Weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.epicodus.pdxfarmshare.Constants.WE_API_BASE_URL;

public class WeatherService {
    private static final String TAG = ConsoleActivity.class.getSimpleName();

    private String city;
    private Double temp;
    private String detail;

    public static void findWeather(Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(WE_API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAM, Constants.WE_API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Weather> processResults(Response response) {
        ArrayList<Weather> currentWeather = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject weatherJSON = new JSONObject(jsonData);
                Log.v(TAG, jsonData);
                city = weatherJSON.getString("name");
                temp = weatherJSON.getJSONObject("main").getDouble("temp");
                detail = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("description");
                Weather weather = new Weather(city, temp, detail);
                currentWeather.add(weather);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return currentWeather;
    }
}
