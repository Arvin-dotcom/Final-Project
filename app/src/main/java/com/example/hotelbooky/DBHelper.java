package com.example.hotelbooky;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.hotelbooky.DBContract.DBEntry.*;

public class DBHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DBHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UsersHotel.db";

//    // Table Names
//    private static final String TABLE_USERS = "Users";
//    private static final String TABLE_BOOKINGS= "Bookings";
//    private static final String TABLE_FAVORITES = "Favorites";
//
//    // Common column names
//    private static final String COL_UID = "id";
//    private static final String COL_EMAIL = "email";
//    private static final String COL_PASSWORD = "password";
//
//    // bookings Table - column nmaes
//    private static final String COL_BOOKING_ID = "booking_id";
//    private static final String COL_ROOM_ID = "room_id";
//    private static final String COL_USER_ID = "user_id";
//    private static final String COL_CREATED_AT = "created_at";
//
//
//    // favorites Table - column names
//    private static final String COL_ROOM_ID_FAV = "room_id";
//    private static final String COL_USER_ID_FAV = "user_id";

    // Table Create Statements
    // Createusers table create statement
    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USERS + "(" + COL_UID + " INTEGER PRIMARY KEY," + COL_EMAIL
            + " TEXT UNIQUE," + COL_PASSWORD + " TEXT)";

    // Create bookings create statement
    private static final String CREATE_TABLE_BOOKINGS = "CREATE TABLE " + TABLE_BOOKINGS
            + "(" + COL_BOOKING_ID + " INTEGER PRIMARY KEY," + COL_HOTEL_ID + " TEXT,"
            + COL_USER_ID + " TEXT," + COL_IMAGE_ID + " TEXT, " + COL_HOTEL_NAME + " TEXT, " + COL_HOTEL_LOC + " TEXT,"
            + COL_CREATED_AT + " TEXT, " + COL_BOOKING_ARRIVAL  +" TEXT, " + COL_BOOKING_DEPARTURE + " TEXT )";

    // Create favorites create statement
    private static final String CREATE_TABLE_FAVORITES = "CREATE TABLE "
            + TABLE_FAVORITES + "(" + COL_FAV_ID + " INTEGER PRIMARY KEY, " + COL_USER_ID + " TEXT,"
            + COL_HOTEL_NAME + " TEXT, " + COL_HOTEL_LOC + " TEXT,"+ COL_IMAGE_ID + " TEXT" + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_FAVORITES);
        db.execSQL(CREATE_TABLE_BOOKINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);

        // create new tables
        onCreate(db);
    }
}
