package com.example.hotelbooky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.hotelbooky.DBContract.DBEntry.COL_BOOKING_ARRIVAL;
import static com.example.hotelbooky.DBContract.DBEntry.COL_BOOKING_DEPARTURE;
import static com.example.hotelbooky.DBContract.DBEntry.COL_BOOKING_ID;
import static com.example.hotelbooky.DBContract.DBEntry.COL_CREATED_AT;
import static com.example.hotelbooky.DBContract.DBEntry.COL_EMAIL;
import static com.example.hotelbooky.DBContract.DBEntry.COL_HOTEL_ID;
import static com.example.hotelbooky.DBContract.DBEntry.COL_HOTEL_LOC;
import static com.example.hotelbooky.DBContract.DBEntry.COL_HOTEL_NAME;
import static com.example.hotelbooky.DBContract.DBEntry.COL_IMAGE_ID;
import static com.example.hotelbooky.DBContract.DBEntry.COL_PASSWORD;
import static com.example.hotelbooky.DBContract.DBEntry.COL_USER_ID;
import static com.example.hotelbooky.DBContract.DBEntry.TABLE_BOOKINGS;
import static com.example.hotelbooky.DBContract.DBEntry.TABLE_FAVORITES;
import static com.example.hotelbooky.DBContract.DBEntry.TABLE_USERS;


public class BookingMgmt {
    public static void insert_bookings(BookingObj booking, Context context) {
        if (!(ver_hotel_name_bk(String.valueOf(booking.getUser_id()), booking.getHotel_name(), context).moveToFirst())) {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            Date currentTime = Calendar.getInstance().getTime();
            values.put(COL_USER_ID, String.valueOf(booking.getUser_id()));
            values.put(COL_HOTEL_ID, booking.getHotel_id());
            values.put(COL_IMAGE_ID, String.valueOf(booking.getHotel_image_id()));
            values.put(COL_HOTEL_NAME, booking.getHotel_name());
            values.put(COL_HOTEL_LOC, booking.getHotel_loc());
//        values.put(COL_USER_ID, );
            //get user id from query
            values.put(COL_BOOKING_ARRIVAL, booking.getArrival_date());
            values.put(COL_BOOKING_DEPARTURE, booking.getDeparture_date());

            long newRowID = db.insert(TABLE_BOOKINGS, null, values);
            Toast.makeText(context, "Successfully booked", Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, String.valueOf(newRowID) + String.valueOf(currentTime), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "You have alread booked " + booking.getHotel_name(), Toast.LENGTH_SHORT).show();
        }

    }

    public static ArrayList<BookingObj> listBookings(String user_id,Context context) {
        DBHelper itemDBHelper = new DBHelper(context);
        String selection = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + COL_USER_ID + " = " + user_id;
        SQLiteDatabase db = itemDBHelper.getReadableDatabase();
        ArrayList<BookingObj> itemObjArrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery(selection, null);
        if (cursor.moveToFirst()) {
            do {
                itemObjArrayList.add(new BookingObj(
                                Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_BOOKING_ID))),
                                Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_USER_ID))),
                                        cursor.getString(cursor.getColumnIndex(COL_HOTEL_ID)),
                                        cursor.getString(cursor.getColumnIndex(COL_HOTEL_NAME)),
                                        cursor.getString(cursor.getColumnIndex(COL_HOTEL_LOC)),
                                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_IMAGE_ID))),
                                        cursor.getString(cursor.getColumnIndex(COL_BOOKING_ARRIVAL)),
                                        cursor.getString(cursor.getColumnIndex(COL_BOOKING_DEPARTURE)))
                );
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return itemObjArrayList;
    }
    public static void deleteBookingItem(BookingObj item, Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = COL_BOOKING_ID+"=?";
        String whereArgs[] = {String.valueOf(item.getBooking_id())};
        db.delete(TABLE_BOOKINGS, whereClause, whereArgs);
    }
    public static Cursor ver_hotel_name_bk(String user_id, String hotel_name, Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String selection = COL_HOTEL_NAME + " = ?";
//        String[] selectionArgs = {hotel_name};
//
//        Cursor cursor = db.query(
//               TABLE_FAVORITES,   // The table to query
//                null,             // The array of columns to return (pass null to get all)
//                selection,              // The columns for the WHERE clause
//                selectionArgs,          // The values for the WHERE clause
//                null,                   // don't group the rows
//                null,                   // don't filter by row groups
//                null// The sort order
//        );
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_BOOKINGS+" WHERE TRIM("+COL_HOTEL_NAME+") = '"+hotel_name.trim()+"'" +
                " AND TRIM("+COL_USER_ID+") = '"+user_id+"'", null);
        return cursor;
    }
}
