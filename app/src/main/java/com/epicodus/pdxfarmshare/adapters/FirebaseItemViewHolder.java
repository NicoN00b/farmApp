package com.epicodus.pdxfarmshare.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.pdxfarmshare.Constants;
import com.epicodus.pdxfarmshare.R;
import com.epicodus.pdxfarmshare.models.Item;
import com.epicodus.pdxfarmshare.ui.ItemDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 11/6/17.
 */

public class FirebaseItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseItemViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindItem(Item item) {
        ImageView itemImageView = (ImageView) mView.findViewById(R.id.itemImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.itemNameTextView);
        TextView locationTextView = (TextView) mView.findViewById(R.id.locationTextView);
        TextView descriptionTextView = (TextView) mView.findViewById(R.id.descriptionTextView);

        nameTextView.setText(item.getName());
        locationTextView.setText(item.getLocation());
        descriptionTextView.setText(item.getDescription());

    }

    @Override
    public void onClick(final View view) {
        final ArrayList<Item> items = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ITEMS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    items.add(snapshot.getValue(Item.class));
                }

                try {

                    int itemPosition = getLayoutPosition();


                    Intent intent = new Intent(mContext, ItemDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("items", Parcels.wrap(items));

                    mContext.startActivity(intent);

                } catch ( ActivityNotFoundException e) {
                     e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
