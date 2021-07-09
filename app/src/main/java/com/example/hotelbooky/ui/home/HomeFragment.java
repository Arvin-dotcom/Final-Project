package com.example.hotelbooky.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooky.HotelCardsAdapter;
import com.example.hotelbooky.HotelObj;
import com.example.hotelbooky.R;
import com.example.hotelbooky.UserObj;

import java.util.ArrayList;

import static com.example.hotelbooky.UserMgmt.listUsers;
import static com.example.hotelbooky.hotel_names_resources.hotelObjArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private HotelCardsAdapter itemAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ArrayList<HotelObj> hotelObjArrayList = new ArrayList<>();

        hotelObjArrayList = hotelObjArrayList(getContext());
        init_recycler(root, hotelObjArrayList);
        return root;
    }

    public String fun_list() {
        String str_out = "";
        ArrayList<UserObj> arr_list_users = new ArrayList<>();
        arr_list_users = listUsers(getContext());
        for (UserObj useritem : arr_list_users) {
            str_out += useritem.toString();
        }

        ArrayList<HotelObj> hotelObjArrayList = new ArrayList<>();
        hotelObjArrayList = hotelObjArrayList(getContext());
        for (HotelObj hotelObj : hotelObjArrayList) {
            str_out += hotelObj.toString();
        }
        return str_out;
    }

    public void init_recycler(View view, ArrayList<HotelObj> itemlist) {
        //        Toast.makeText(getApplicationContext(), itemlist.get(0).toString(), Toast.LENGTH_SHORT).show();
        recyclerView = view.findViewById(R.id.hotel_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        itemAdapter = new HotelCardsAdapter(itemlist);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new GridLayoutManager(view.getApplicationContext(), 2));
        recyclerView.setAdapter(itemAdapter);
    }
}