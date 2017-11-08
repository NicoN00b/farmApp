package com.epicodus.pdxfarmshare.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.pdxfarmshare.Constants;
import com.epicodus.pdxfarmshare.R;
import com.epicodus.pdxfarmshare.adapters.FirebaseItemViewHolder;
import com.epicodus.pdxfarmshare.models.Item;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemDetailFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.itemImageView) ImageView mImageLabel;
    @Bind(R.id.itemNameTextView) TextView mNameLabel;
    @Bind(R.id.descriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.locationTextView) TextView mLocationLabel;
    @Bind(R.id.saveItemButton) Button mSaveItemButton;
    @Bind(R.id.messageButton) Button mSendMessageButton;
    SharedPreferences mSharedPreferences;
    private  SharedPreferences.Editor mEditor;


    private DatabaseReference mItemReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    private Item mItem;
    String pushId;

    public static ItemDetailFragment newInstance(Item item) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("item", Parcels.wrap(item));
        itemDetailFragment.setArguments(args);
        return itemDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItem = Parcels.unwrap(getArguments().getParcelable("item"));



        mItemReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_ITEMS)
                .child("item");


        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        mEditor = mSharedPreferences.edit();


        setUpFirebaseAdapter();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        ButterKnife.bind(this, view);

//        Picasso.with(view.getContext())
//                .load(mItem.getImageUrl())
//                .resize(MAX_WIDTH, MAX_HEIGHT)
//                .centerCrop()
//                .into(mImageLabel);

        mNameLabel.setText(mItem.getName());
        mDescriptionLabel.setText(mItem.getDescription());
        mLocationLabel.setText(mItem.getLocation());
        mSaveItemButton.findViewById(R.id.saveItemButton);
        mSendMessageButton.findViewById(R.id.messageButton);
//
//        mPhoneLabel.setOnClickListener(this);
        mLocationLabel.setOnClickListener(this);
        mSaveItemButton.setOnClickListener(this);
        mSendMessageButton.setOnClickListener(this);


        return view;
    }



    @Override
    public void onClick(View v) {
//        if (v == mPhoneLabel) {
//            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
//                    Uri.parse("tel:" + mItem.getPhone()));
//            startActivity(phoneIntent);
//        }
        if (v == mLocationLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=(" + mItem.getLocation() + ")"));
            startActivity(mapIntent);
        }

        if (v == mSendMessageButton) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setData(Uri.parse("email"));
            String[] s = {"abc@gmail.com","xyz@gmail.com"};
            i.putExtra(Intent.EXTRA_EMAIL, s);
            i.putExtra(Intent.EXTRA_SUBJECT, "This is a subject");
            i.putExtra(Intent.EXTRA_TEXT, "Here is the body of an Email");
            i.setType("message/rfc822");
            Intent chooser = Intent.createChooser(i, "Launch Email");
            startActivity(chooser);
        }

        if (v == mSaveItemButton) {
            addToSharedPreferences(pushId);
            Toast.makeText(getActivity(), "Item saved to your favorites", Toast.LENGTH_LONG).show();
        }
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
    }

    private void addToSharedPreferences(String pushId) {
        mEditor.putString(Constants.PREFERENCES_FAVORITE_KEY, pushId).apply();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

}
