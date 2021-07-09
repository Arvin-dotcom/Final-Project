package com.example.hotelbooky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class LogoutFragment extends Fragment {
    public static final String SHARED_PREFS = "userlogin";
    public static final String USERNAME_TEXT = "none";
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view=  inflater.inflate(R.layout.fragment_logout, container, false);
         saveloginData("");
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
        return view;
    }

    public void saveloginData(String username) {
        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USERNAME_TEXT, username);

        editor.apply();

        Toast.makeText(view.getContext(), "Login data saved", Toast.LENGTH_SHORT).show();
    }
}