package com.example.hotelbooky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.hotelbooky.WishlistMgmt.deleteWishItem;

public class DisplayWishListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wish_list);
        WishListObj hotelObj = (WishListObj) getIntent().getSerializableExtra("hotel_obj");
        TextView hotel_location;
        ImageView hotel_img;
        hotel_location = findViewById(R.id.disp_wish_hotel_loc);
        hotel_img = findViewById(R.id.disp_wish_img);
        getSupportActionBar().setTitle(hotelObj.getHotel_name());
        hotel_img.setImageResource(hotelObj.getHotel_img_id());
        hotel_location.setText(hotelObj.getHotel_loc());
        findViewById(R.id.del_wishlist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteWishItem(hotelObj, v.getContext());
                finish();
            }
        });
    }
}