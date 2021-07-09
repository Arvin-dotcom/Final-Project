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
import static com.example.hotelbooky.DBContract.DBEntry.COL_FAV_ID;
import static com.example.hotelbooky.DBContract.DBEntry.COL_HOTEL_ID;
import static com.example.hotelbooky.DBContract.DBEntry.COL_HOTEL_LOC;
import static com.example.hotelbooky.DBContract.DBEntry.COL_HOTEL_NAME;
import static com.example.hotelbooky.DBContract.DBEntry.COL_IMAGE_ID;
import static com.example.hotelbooky.DBContract.DBEntry.COL_USER_ID;
import static com.example.hotelbooky.DBContract.DBEntry.TABLE_BOOKINGS;
import static com.example.hotelbooky.DBContract.DBEntry.TABLE_FAVORITES;

public class WishlistMgmt {
    public static void insert_favorites(WishListObj booking, Context context) {
        if (!(ver_hotel_name_ws(String.valueOf(booking.getUid()), booking.getHotel_name(), context).moveToFirst())){
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            Date currentTime = Calendar.getInstance().getTime();
            values.put(COL_USER_ID, String.valueOf(booking.getUid()));
            values.put(COL_IMAGE_ID, String.valueOf(booking.getHotel_img_id()));
            values.put(COL_HOTEL_NAME, booking.getHotel_name());
            values.put(COL_HOTEL_LOC, booking.getHotel_loc());
//        values.put(COL_USER_ID, );
            //get user id from query
            long newRowID = db.insert(TABLE_FAVORITES, null, values);
            Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show();
//        Toast.makeText(context, String.valueOf(newRowID) + String.valueOf(currentTime), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, booking.getHotel_name() + " already exists in favorites", Toast.LENGTH_SHORT).show();

        }

    }

    public static Cursor ver_hotel_name_ws(String user_id, String hotel_name, Context context) {
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
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_FAVORITES+" WHERE TRIM("+COL_HOTEL_NAME+") = '"+hotel_name.trim()+"'" +
                " AND TRIM("+COL_USER_ID+") = '"+user_id+"'", null);
        return cursor;
    }

    public static ArrayList<WishListObj> listFavorites(String user_id, Context context) {
        DBHelper itemDBHelper = new DBHelper(context);
        String selection = "SELECT * FROM " + TABLE_FAVORITES + " WHERE " + COL_USER_ID + " = " + user_id;
        SQLiteDatabase db = itemDBHelper.getReadableDatabase();
        ArrayList<WishListObj> itemObjArrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery(selection, null);
        if (cursor.moveToFirst()) {
            do {
                itemObjArrayList.add(new WishListObj(
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_USER_ID))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_FAV_ID))),
                        cursor.getString(cursor.getColumnIndex(COL_HOTEL_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_HOTEL_LOC)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_IMAGE_ID)))
                ));
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return itemObjArrayList;
    }

    public static void deleteWishItem(WishListObj item, Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = COL_FAV_ID+"=?";
        String whereArgs[] = {String.valueOf(item.getFav_id())};
        db.delete(TABLE_FAVORITES, whereClause, whereArgs);
        Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
    }
}
