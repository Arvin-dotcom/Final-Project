package com.example.hotelbooky;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class HotelCardsAdapter extends RecyclerView.Adapter<HotelCardsAdapter.MyViewHolder> {
    private ArrayList<HotelObj> ItemList;


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
    public HotelCardsAdapter(ArrayList<HotelObj> myDataset) {
        this.ItemList = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HotelCardsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_hotel_cards, parent, false);
        HotelCardsAdapter.MyViewHolder vh = new HotelCardsAdapter.MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final HotelCardsAdapter.MyViewHolder holder, final int position) {
        final HotelObj itemlist_obj = ItemList.get(position);
        holder.hotel_name.setText(itemlist_obj.getHotel_name());
        holder.hotel_location.setText(itemlist_obj.getLocation());
        holder.hotel_image.setImageResource(itemlist_obj.getImage_id());

        holder.hotel_card_clickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DisplayHotelActivity.class);
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

    public void filterList(ArrayList<HotelObj> filteredList) {
        ItemList = filteredList;
        notifyDataSetChanged();
    }
}
