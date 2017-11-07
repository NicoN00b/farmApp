package com.epicodus.pdxfarmshare.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.pdxfarmshare.models.Item;
import com.epicodus.pdxfarmshare.ui.ItemDetailFragment;

import java.util.ArrayList;


public class ItemPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Item> mItems;

    public ItemPagerAdapter(FragmentManager fm, ArrayList<Item> items) {
        super(fm);
        mItems = items;
    }

    @Override
    public Fragment getItem(int position) {
        return ItemDetailFragment.newInstance(mItems.get(position));
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position).getName();
    }
}
