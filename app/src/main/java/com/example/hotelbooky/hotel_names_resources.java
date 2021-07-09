package com.example.hotelbooky;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class hotel_names_resources {
    public static ArrayList<HotelObj> hotelObjArrayList(Context context){
        ArrayList<HotelObj> hotelObjArrayList_tmp = new ArrayList<>();
        String[] hotel_names =  context.getResources().getStringArray(R.array.hotel_names);
        String[] hotel_locs =  context.getResources().getStringArray(R.array.hotel_locs);
        String[] hotel_rates =  context.getResources().getStringArray(R.array.hotel_rates);
        for (int counter = 0; counter < 30; counter++){
            String mDrawableName = "hotel_images_" + String.valueOf(counter+1);
            int resID = context.getResources().getIdentifier(mDrawableName , "drawable", context.getPackageName());
            hotelObjArrayList_tmp.add(new HotelObj(counter+1, hotel_names[counter],
                    hotel_locs[counter],
                    hotel_rates[counter],
                    resID
            ));
        }
//        hotelObjArrayList_tmp.add(new HotelObj(1, "Puerto Del Sol Resort",
//                "Barangay Ilog Malino, Patar Beach, Bolinao, Philippines, 2406", "4062",
//                ))
        return hotelObjArrayList_tmp;
    }
}
