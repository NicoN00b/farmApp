package com.epicodus.pdxfarmshare.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.epicodus.pdxfarmshare.Constants;
import com.epicodus.pdxfarmshare.adapters.FirebaseItemViewHolder;
import com.epicodus.pdxfarmshare.adapters.ItemListAdapter;
import com.epicodus.pdxfarmshare.R;
import com.epicodus.pdxfarmshare.adapters.ItemListAdapter;
import com.epicodus.pdxfarmshare.models.Item;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.epicodus.pdxfarmshare.R.id.itemImageView;

public class ItemsActivity extends AppCompatActivity {
    public static final String TAG = ItemsActivity.class.getSimpleName();



    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private ItemListAdapter mAdapter;
    private DatabaseReference mItemReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;

    public ArrayList<Item> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mItemReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_ITEMS)
                .child(uid);

        setUpFirebaseAdapter();

    }



    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Item, FirebaseItemViewHolder>(Item.class,
                R.layout.item_list, FirebaseItemViewHolder.class, mItemReference) {
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

