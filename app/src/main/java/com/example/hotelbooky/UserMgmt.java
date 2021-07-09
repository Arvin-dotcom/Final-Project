package com.example.hotelbooky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.hotelbooky.DBContract.DBEntry.*;

public class UserMgmt {
    public static void insert_user(UserObj user, Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, user.getEmail());
        values.put(COL_PASSWORD, user.getPassword());
        long newRowID = db.insert(TABLE_USERS, null, values);
        Toast.makeText(context, "Successfully registered", Toast.LENGTH_SHORT).show();
    }
    public static boolean is_registered(String username, Context context){
        if (fetch_info(username, context).moveToFirst()){
            return true;
        }else {
            return false;
        }
    }

    public static Cursor fetch_info(String username, Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = COL_EMAIL + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_USERS,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null// The sort order
        );
        return cursor;
    }

    public static String get_User_id(String username, Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = COL_EMAIL + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_USERS,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null// The sort order
        );
        String uid = "";
        if (cursor.moveToFirst()) {
            do {
                uid += cursor.getString(cursor.getColumnIndex(COL_UID));


            } while (cursor.moveToNext());
        }
        db.close();
        return uid;
    }

    public static ArrayList<UserObj> listUsers(Context context) {
        DBHelper itemDBHelper = new DBHelper(context);
        String selection = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = itemDBHelper.getReadableDatabase();
        ArrayList<UserObj> itemObjArrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery(selection, null);
        if (cursor.moveToFirst()) {
            do {
                itemObjArrayList.add(new UserObj(
                                cursor.getString(cursor.getColumnIndex(COL_EMAIL)),
                                cursor.getString(cursor.getColumnIndex(COL_PASSWORD))
                        )
                );

            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return itemObjArrayList;
    }

}
