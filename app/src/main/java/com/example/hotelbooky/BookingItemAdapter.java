package com.example.hotelbooky;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;

import java.util.ArrayList;

public class BookingItemAdapter extends RecyclerView.Adapter<BookingItemAdapter.MyViewHolder> {
    private ArrayList<BookingObj> ItemList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView hotel_name, hotel_desc;
        public ImageView hotel_image;
        public MaterialCardView hotel_card_clickable;

        public MyViewHolder(View v) {
            super(v);
            hotel_name = v.findViewById(R.id.booking_hotel_name);
            hotel_desc = v.findViewById(R.id.booking_desc);
            hotel_image = v.findViewById(R.id.booking_hotel_card_image);
            hotel_card_clickable = v.findViewById(R.id.booking_hotel_clickable);

//            quiz_name = v.findViewById(R.id.menu_quiz_title);

        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public BookingItemAdapter(ArrayList<BookingObj> myDataset) {
        this.ItemList = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BookingItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_booking_cards, parent, false);
        BookingItemAdapter.MyViewHolder vh = new BookingItemAdapter.MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final BookingItemAdapter.MyViewHolder holder, final int position) {
        final BookingObj itemlist_obj = ItemList.get(position);

        holder.hotel_name.setText(itemlist_obj.getHotel_name());
        holder.hotel_desc.setText("Arrival: " + itemlist_obj.getArrival_date() +
                " Departure: " + itemlist_obj.getDeparture_date());
        holder.hotel_image.setImageResource(itemlist_obj.getHotel_image_id());

        holder.hotel_card_clickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), MyBookingsItemActivity.class);
                intent.putExtra("book_obj", itemlist_obj);
                holder.itemView.getContext().startActivity(intent);
//                intent.putExtra("hotel_obj", itemlist_obj);
//                holder.itemView.getContext().startActivity(intent);

            }
        });




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ItemList.size();
    }




    public void filterList(ArrayList<BookingObj> filteredList) {
        ItemList = filteredList;
//        notifyDataSetChanged();
    }
}
