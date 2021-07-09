package com.example.hotelbooky;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.MyViewHolder> {
    private ArrayList<WishListObj> ItemList;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView hotel_name, hotel_location;
        public ImageView hotel_image;
        public MaterialCardView hotel_card_clickable;

        public MyViewHolder(View v) {
            super(v);
            hotel_name = v.findViewById(R.id.hotel_name);
            hotel_location = v.findViewById(R.id.hotel_location);
            hotel_image = v.findViewById(R.id.hotel_card_image);
            hotel_card_clickable = v.findViewById(R.id.hotel_card_clickable);

//            quiz_name = v.findViewById(R.id.menu_quiz_title);

        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public WishListAdapter(ArrayList<WishListObj> myDataset) {
        this.ItemList = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public WishListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_hotel_cards, parent, false);
        WishListAdapter.MyViewHolder vh = new WishListAdapter.MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final WishListAdapter.MyViewHolder holder, final int position) {
        final WishListObj itemlist_obj = ItemList.get(position);
        holder.hotel_name.setText(itemlist_obj.getHotel_name());
        holder.hotel_location.setText(itemlist_obj.getHotel_loc());
        holder.hotel_image.setImageResource(itemlist_obj.getHotel_img_id());

        holder.hotel_card_clickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DisplayWishListActivity.class);
                intent.putExtra("hotel_obj", itemlist_obj);
                holder.itemView.getContext().startActivity(intent);

            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ItemList.size();
    }

    public void filterList(ArrayList<WishListObj> filteredList) {
        ItemList = filteredList;
        notifyDataSetChanged();
    }
}
