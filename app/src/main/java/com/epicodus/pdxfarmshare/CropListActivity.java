package com.epicodus.pdxfarmshare;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.MapFragment;

import static com.epicodus.pdxfarmshare.R.id.map;

public class CropListActivity extends AppCompatActivity {
    private String[] crops = new String[] {"kale", "beets", "rosemary", "lavender", "peaches", "cherries", "apples", "chard", "blackberries"};
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, crops);
        mListView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()  {
                @Override
                public void onClick(View v) {
                    MapFragment mapFragment = (MapFragment) getFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
                }
        });

    }

}
