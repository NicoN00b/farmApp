package com.epicodus.pdxfarmshare.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

//import com.epicodus.pdxfarmshare.adapters.ItemListAdapter;
import com.epicodus.pdxfarmshare.R;
import com.epicodus.pdxfarmshare.models.Item;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemsActivity extends AppCompatActivity {
    private String[] crops = new String[] {"kale", "beets", "rosemary", "lavender", "peaches", "cherries", "apples", "chard", "blackberries"};
    public static final String TAG = ItemsActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
//    private ItemListAdapter mAdapter;

    public ArrayList<Item> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getItems(location);
    }

    private void getItems(String location) {
//
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                mItems = yelpService.processResults(response);
//
//                ItemsActivity.this.runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        mAdapter = new ItemListAdapter(getApplicationContext(), mItems);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager =
//                                new LinearLayoutManager(ItemsActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//                    }
//                });
//            }
//        });
    }

}

