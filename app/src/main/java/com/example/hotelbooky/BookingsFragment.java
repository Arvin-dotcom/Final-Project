package com.example.hotelbooky;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.hotelbooky.BookingMgmt.listBookings;
import static com.example.hotelbooky.HomeActivity.USER_NAME_GLOBAL;
import static com.example.hotelbooky.UserMgmt.get_User_id;


public class BookingsFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookingItemAdapter itemAdapter;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view =  inflater.inflate(R.layout.fragment_bookings, container, false);
        TextView bookings_txt = view.findViewById(R.id.text_bookings_log);
        String getUid = get_User_id(USER_NAME_GLOBAL, view.getContext());
        ArrayList<BookingObj> bookingObjs = new ArrayList<>();
        bookingObjs = listBookings(getUid, view.getContext());
        init_recycler(view, bookingObjs);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        String getUid = get_User_id(USER_NAME_GLOBAL, view.getContext());
        ArrayList<BookingObj> bookingObjs = new ArrayList<>();
        bookingObjs = listBookings(getUid, view.getContext());
        init_recycler(view, bookingObjs);

    }

    
    public void init_recycler(View view, ArrayList<BookingObj> itemlist) {
        //        Toast.makeText(getApplicationContext(), itemlist.get(0).toString(), Toast.LENGTH_SHORT).show();
        recyclerView = view.findViewById(R.id.booking_card_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        itemAdapter = new BookingItemAdapter(itemlist);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new GridLayoutManager(view.getApplicationContext(), 2));
        recyclerView.setAdapter(itemAdapter);
    }
}