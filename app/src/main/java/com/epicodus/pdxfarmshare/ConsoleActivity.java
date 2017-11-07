package com.epicodus.pdxfarmshare;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.pdxfarmshare.adapters.FirebaseItemViewHolder;
import com.epicodus.pdxfarmshare.models.Item;
import com.epicodus.pdxfarmshare.models.Weather;
import com.epicodus.pdxfarmshare.ui.CreateItemActivity;
import com.epicodus.pdxfarmshare.ui.MainActivity;
import com.epicodus.pdxfarmshare.ui.MapsActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private DatabaseReference mItemReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    private static final String TAG = ConsoleActivity.class.getSimpleName();


    @Bind(R.id.weatherTextView) TextView mWeatherTextView;
    @Bind(R.id.recyclerView)  RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_console);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mItemReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_ITEMS)
                .child(uid);

        setUpFirebaseAdapter();

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

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Item, FirebaseItemViewHolder>(Item.class, R.layout.item_list, FirebaseItemViewHolder.class, mItemReference) {
            @Override
            protected void populateViewHolder(FirebaseItemViewHolder viewHolder,
                                              Item model, int position) {
                viewHolder.bindItem(model);

            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

}
