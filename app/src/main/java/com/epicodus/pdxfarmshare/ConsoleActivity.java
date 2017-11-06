package com.epicodus.pdxfarmshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.pdxfarmshare.models.Weather;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ConsoleActivity extends AppCompatActivity {

    private static final String TAG = ConsoleActivity.class.getSimpleName();

    @Bind(R.id.weatherTextView) TextView mWeatherTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_console);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        String temp = intent.getStringExtra("temp");
        String description = intent.getStringExtra("description");

        mWeatherTextView.setText("The current temperature in " + city + " is " + temp + "Fahrenheit, with " + description);

        getWeather();

    }

    private void getWeather(){
        final WeatherService weatherService = new WeatherService();
        weatherService.findWeather(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v(TAG, jsonData);
                        weatherService.processResults(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
