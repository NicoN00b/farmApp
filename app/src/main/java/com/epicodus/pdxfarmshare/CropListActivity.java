package com.epicodus.pdxfarmshare;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.MapFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.epicodus.pdxfarmshare.R.id.map;

public class CropListActivity extends AppCompatActivity {
    private String[] crops = new String[] {"kale", "beets", "rosemary", "lavender", "peaches", "cherries", "apples", "chard", "blackberries"};
    @Bind(R.id.listView) ListView mListView;
    private MapFragment mMapFragment;

    private static final int GPS_ERRORDIALOG_REQUEST = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_crop_list);

        if (servicesOK()) {
            Toast.makeText(this, "Ready to Map", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_maps);
        }
        else {
            setContentView(R.layout.activity_crop_list);
        }

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ButterKnife.bind(this);


        mListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, crops);
        mListView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()  {
                @Override
                public void onClick(View v) {
                    mMapFragment = MapFragment.newInstance();

                    FragmentTransaction fragmentTransaction =
                            getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.crops, mMapFragment);
                    fragmentTransaction.commit();

                }
        });

    }

    public boolean servicesOK() {
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, GPS_ERRORDIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(this, "Cannot connect to Google Play Services", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
