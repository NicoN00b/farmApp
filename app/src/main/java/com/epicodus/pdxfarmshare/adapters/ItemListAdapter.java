//package com.epicodus.pdxfarmshare.adapters;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.epicodus.pdxfarmshare.R;
//import com.epicodus.pdxfarmshare.models.Item;
//import com.epicodus.pdxfarmshare.ui.ItemDetailActivity;
//import com.squareup.picasso.Picasso;
//
//import org.parceler.Parcels;
//
//import java.util.ArrayList;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
//
//public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {
//    private static final int MAX_WIDTH = 200;
//    private static final int MAX_HEIGHT = 200;
//
//    private ArrayList<Item> mItems = new ArrayList<>();
//    private Context mContext;
//
//    public ItemListAdapter(Context context, ArrayList<Item> items) {
//        mContext = context;
//        mItems = items;
//    }
//
//    @Override
//    public ItemListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items, parent, false);
//        ItemViewHolder viewHolder = new ItemViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ItemListAdapter.ItemViewHolder holder, int position) {
//        holder.bindItem(mItems.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return mItems.size();
//    }
//
//    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        @Bind(R.id.itemImageView)
//        ImageView mItemImageView;
//        @Bind(R.id.itemNameTextView)
//        TextView mNameTextView;
//        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
//        @Bind(R.id.ratingTextView) TextView mRatingTextView;
//
//        private Context mContext;
//
//        public ItemViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//
//            mContext = itemView.getContext();
//            itemView.setOnClickListener(this);
//        }
//
//        public void bindItem(Item item) {
//            Picasso.with(mContext)
//                    .load(item.getImageUrl())
//                    .resize(MAX_WIDTH, MAX_HEIGHT)
//                    .centerCrop()
//                    .into(mItemImageView);
//
//            mNameTextView.setText(item.getName());
//        }
//
//        @Override
//        public void onClick(View v) {
//            Log.d("click listener", "working!");
//            int itemPosition = getLayoutPosition();
//            Intent intent = new Intent(mContext, ItemDetailActivity.class);
//            intent.putExtra("position", itemPosition + "");
//            intent.putExtra("items", Parcels.wrap(mItems));
//            mContext.startActivity(intent);
//        }
//    }
//}
