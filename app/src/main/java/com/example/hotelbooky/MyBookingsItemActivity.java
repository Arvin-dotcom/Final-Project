package com.example.hotelbooky;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.hotelbooky.BookingMgmt.deleteBookingItem;

public class MyBookingsItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings_item);
        BookingObj hotelObj = (BookingObj) getIntent().getSerializableExtra("book_obj");
        TextView text_hotel_Name, hotel_location, hotel_desc;
        ImageView hotel_img;
        hotel_img = findViewById(R.id.my_bookings_hotel_img);
        text_hotel_Name = findViewById(R.id.my_bookings_hotel_name);
        hotel_location = findViewById(R.id.my_bookings_hotel_location);
        hotel_desc = findViewById(R.id.my_bookings_hotel_desc);
        getSupportActionBar().setTitle(hotelObj.getHotel_name());
        hotel_img.setImageResource(hotelObj.getHotel_image_id());
        text_hotel_Name.setText(hotelObj.getHotel_name());
        hotel_location.setText(hotelObj.getHotel_loc());
        String hotel_ar_dp = "Arrival date: " + hotelObj.getArrival_date() + "\nDeparture date: " + hotelObj.getDeparture_date();
        hotel_desc.setText(hotel_ar_dp);

        findViewById(R.id.delete_booking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBookingItem(hotelObj, v.getContext());
                Toast.makeText(MyBookingsItemActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
                finish();
//                v.getContext().startActivity(intent);
            }
        });


    }
}