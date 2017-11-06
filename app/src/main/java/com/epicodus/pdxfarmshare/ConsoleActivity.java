package com.epicodus.pdxfarmshare;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.pdxfarmshare.models.Weather;
import com.epicodus.pdxfarmshare.ui.CreateItemActivity;
import com.epicodus.pdxfarmshare.ui.MainActivity;
import com.epicodus.pdxfarmshare.ui.MapsActivity;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.epicodus.pdxfarmshare.WeatherService.findWeather;

public class ConsoleActivity extends AppCompatActivity {

    private static final String TAG = ConsoleActivity.class.getSimpleName();


    @Bind(R.id.weatherTextView) TextView mWeatherTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_console);
        ButterKnife.bind(this);

        getWeather();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsoleActivity.this, CreateItemActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getWeather(){
        final WeatherService weatherService = new WeatherService();
        findWeather(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String outputFromAsync = weatherService.processResults(response);
                ConsoleActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWeatherTextView
                                .setText(outputFromAsync);
                    }
                });
            }

        });


    }

}
