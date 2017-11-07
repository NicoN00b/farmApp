package com.epicodus.pdxfarmshare.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.pdxfarmshare.R;
import com.epicodus.pdxfarmshare.models.Item;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ItemDetailFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.itemImageView) ImageView mImageLabel;
    @Bind(R.id.itemNameTextView) TextView mNameLabel;
//    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
//    @Bind(R.id.addressTextView) TextView mAddressLabel;
//    @Bind(R.id.saveItemButton) TextView mSaveItemButton;

    private Item mItem;

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
//        mPhoneLabel.setText(mItem.getPhone());
//        mAddressLabel.setText(android.text.TextUtils.join(", ", mItem.getAddress()));
//
//        mPhoneLabel.setOnClickListener(this);
//        mAddressLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
//        if (v == mPhoneLabel) {
//            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
//                    Uri.parse("tel:" + mItem.getPhone()));
//            startActivity(phoneIntent);
//        }
//        if (v == mAddressLabel) {
//            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
//                    Uri.parse("geo:" + mItem.getLatitude()
//                            + "," + mItem.getLongitude()
//                            + "?q=(" + mItem.getName() + ")"));
//            startActivity(mapIntent);
//        }
    }
}
