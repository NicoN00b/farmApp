package com.epicodus.pdxfarmshare;

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
            JSONObject weatherJSON = new JSONObject(jsonData);
            String city = weatherJSON.getString("name");
            String temp = weatherJSON.getString("temp");
            String description = weatherJSON.getString("description");
            Weather weather = new Weather(city, temp, description);
            currentWeather.add(weather);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return currentWeather;
    }
}
