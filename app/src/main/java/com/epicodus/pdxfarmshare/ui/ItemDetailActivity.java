package com.epicodus.pdxfarmshare.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.epicodus.pdxfarmshare.Constants;
import com.epicodus.pdxfarmshare.R;
import com.epicodus.pdxfarmshare.adapters.ItemPagerAdapter;
import com.epicodus.pdxfarmshare.models.Item;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ItemDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private ItemPagerAdapter adapterViewPager;
    ArrayList<Item> mItems = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);


        mItems = Parcels.unwrap(getIntent().getParcelableExtra("items"));

        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        adapterViewPager = new ItemPagerAdapter(getSupportFragmentManager(), mItems);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

    }


}
