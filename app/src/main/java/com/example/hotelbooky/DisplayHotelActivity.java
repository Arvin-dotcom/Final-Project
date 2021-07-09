package com.example.hotelbooky;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.Serializable;

import static com.example.hotelbooky.BookingMgmt.insert_bookings;
import static com.example.hotelbooky.HomeActivity.USER_NAME_GLOBAL;
import static com.example.hotelbooky.UserMgmt.get_User_id;
import static com.example.hotelbooky.WishlistMgmt.insert_favorites;

public class DisplayHotelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_hotel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        TextView txtrates;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        HotelObj hotelObj = (HotelObj) getIntent().getSerializableExtra("hotel_obj");
//        String hotel_id = getIntent().getStringExtra("hotel_id");
//        String hotel_name = getIntent().getStringExtra("hotel_name");
//        int image_id = getIntent().getIntExtra("image_id", 0);
        toolBarLayout.setTitle(hotelObj.getHotel_name());
        appBarLayout.setBackgroundResource(hotelObj.getImage_id());
        txtrates = findViewById(R.id.text_rates);
        txtrates.setText("₱ " + hotelObj.getRates());
        EditText ed_date_arrival, ed_date_departure;
        ed_date_arrival = findViewById(R.id.arrival_date_txt);
        ed_date_departure = findViewById(R.id.departure_date_txt);
        String getUid = get_User_id(USER_NAME_GLOBAL, getApplicationContext());

        findViewById(R.id.btn_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(DisplayHotelActivity.this, getUid, Toast.LENGTH_SHORT).show();
                if (!(ed_date_arrival.getText().toString().equals("") || ed_date_departure.getText().toString().equals(""))){
                    BookingObj bookingObj = new BookingObj(1,
                            Integer.parseInt(getUid),
                            String.valueOf(hotelObj.getHotel_id()),
                            hotelObj.getHotel_name(),
                            hotelObj.getLocation(),
                            hotelObj.getImage_id(),
                            ed_date_arrival.getText().toString(),
                            ed_date_departure.getText().toString());
                    insert_bookings(bookingObj, getApplicationContext());
                }else{
                    Toast.makeText(DisplayHotelActivity.this, "Please enter somo", Toast.LENGTH_SHORT).show();
                }

            }
        });
        findViewById(R.id.btn_add_wish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishListObj wishListObj = new WishListObj(Integer.parseInt(getUid), 1,
                        hotelObj.getHotel_name(),
                        hotelObj.getLocation(),
                        hotelObj.getImage_id());
                insert_favorites(wishListObj, v.getContext());
            }
        });


    }
}