package com.example.hotelbooky;

import android.provider.BaseColumns;

public final class DBContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor public.
    private DBContract() {}

    /* Inner class that defines the table contents */
    public static class DBEntry implements BaseColumns {

        // Table Names
        public static final String TABLE_USERS = "Users";
        public static final String TABLE_BOOKINGS= "Bookings";
        public static final String TABLE_FAVORITES = "Favorites";

        // Common column names
        public static final String COL_UID = "id";
        public static final String COL_EMAIL = "email";
        public static final String COL_PASSWORD = "password";


        // bookings Table - column nmaes
        public static final String COL_BOOKING_ID = "booking_id";
        public static final String COL_HOTEL_ID = "hotel_id";
        public static final String COL_USER_ID = "user_id";
        public static final String COL_IMAGE_ID = "image_id";
        public static final String COL_HOTEL_NAME = "hotel_name";
        public static final String COL_HOTEL_LOC = "hotel_loc";
        public static final String COL_BOOKING_ARRIVAL = "arrival";
        public static final String COL_BOOKING_DEPARTURE = "depature";
        public static final String COL_CREATED_AT = "created_at";


        // favorites Table - column names
        public static final String COL_FAV_ID = "fav_id";
//        public static final String COL_IMAGE_ID = "image_id";
//        public static final String COL_HOTEL_NAME = "hotel_name";
//        public static final String COL_HOTEL_LOC = "hotel_loc";
//        public static final String COL_ROOM_ID_FAV = "room_id";
//        public static final String COL_USER_ID_FAV = "user_id";
    }
}
