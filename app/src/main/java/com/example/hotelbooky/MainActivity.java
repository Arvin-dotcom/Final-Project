package com.example.hotelbooky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import static com.example.hotelbooky.DBContract.DBEntry.COL_EMAIL;
import static com.example.hotelbooky.DBContract.DBEntry.COL_PASSWORD;
import static com.example.hotelbooky.UserMgmt.fetch_info;
import static com.example.hotelbooky.UserMgmt.insert_user;
import static com.example.hotelbooky.UserMgmt.is_registered;

public class MainActivity extends AppCompatActivity {
    private UserObj userObj;
    EditText email_ed, password_ed;
    public static final String SHARED_PREFS = "userlogin";
    public static final String USERNAME_TEXT = "none";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (loadsaveloginData().equals("")){
            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Have recent login " + loadsaveloginData(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra("userName" , loadsaveloginData());
            startActivity(intent);

        }
        email_ed = findViewById(R.id.username_txt);
        password_ed = findViewById(R.id.password_txt);
        findViewById(R.id.reg_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userObj = new UserObj(email_ed.getText().toString(), password_ed.getText().toString());
                if (is_registered(userObj.getEmail(), getApplicationContext())){
                    Toast.makeText(MainActivity.this, userObj.getEmail() + "is already registered\nLogin instead", Toast.LENGTH_SHORT).show();
                }else {
                    insert_user(userObj, getApplicationContext());
                }
            }
        });
        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = fetch_info(email_ed.getText().toString(), getApplicationContext());
                if (cursor.moveToFirst()){
                    if (password_ed.getText().toString().equals(cursor.getString(cursor.getColumnIndex(COL_PASSWORD)))){
                        Intent intent = new Intent(v.getContext(), HomeActivity.class);
                        intent.putExtra("userName" , cursor.getString(cursor.getColumnIndex(COL_EMAIL)));
                        saveloginData(cursor.getString(cursor.getColumnIndex(COL_EMAIL)));
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "Invalid username/password", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Invalid username/password", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    public void saveloginData(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USERNAME_TEXT, username);

        editor.apply();

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }
    public String loadsaveloginData() {
        String username_tmp_login = "";
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        username_tmp_login = sharedPreferences.getString(USERNAME_TEXT, "");
        return username_tmp_login;
    }



}