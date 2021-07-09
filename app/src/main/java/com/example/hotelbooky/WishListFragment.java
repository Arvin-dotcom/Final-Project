package com.example.hotelbooky;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static com.example.hotelbooky.BookingMgmt.listBookings;
import static com.example.hotelbooky.HomeActivity.USER_NAME_GLOBAL;
import static com.example.hotelbooky.UserMgmt.get_User_id;
import static com.example.hotelbooky.UserMgmt.listUsers;
import static com.example.hotelbooky.WishlistMgmt.listFavorites;
import static com.example.hotelbooky.hotel_names_resources.hotelObjArrayList;

public class WishListFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private WishListAdapter itemAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_wish_list, container, false);
        String getUid = get_User_id(USER_NAME_GLOBAL, view.getContext());
        ArrayList<WishListObj> wishlistobj = new ArrayList<>();
        wishlistobj = listFavorites(getUid, view.getContext());
        init_recycler(view, wishlistobj);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String getUid = get_User_id(USER_NAME_GLOBAL, view.getContext());
        ArrayList<WishListObj> wishlistobj = new ArrayList<>();
        wishlistobj = listFavorites(getUid, view.getContext());
        init_recycler(view, wishlistobj);
    }

    public void init_recycler(View view, ArrayList<WishListObj> itemlist) {
        //        Toast.makeText(getApplicationContext(), itemlist.get(0).toString(), Toast.LENGTH_SHORT).show();
        recyclerView = view.findViewById(R.id.wish_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        itemAdapter = new WishListAdapter(itemlist);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new GridLayoutManager(view.getApplicationContext(), 2));
        recyclerView.setAdapter(itemAdapter);
    }
}