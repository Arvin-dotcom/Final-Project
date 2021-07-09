package com.example.hotelbooky;

import java.io.Serializable;

public class BookingObj implements Serializable {
    private int booking_id;
    private int user_id;
    private String hotel_id;
    private String hotel_name;
    private String hotel_loc;
    private int hotel_image_id;
    private String arrival_date;
    private String departure_date;

    public BookingObj(int booking_id, int user_id, String hotel_id, String hotel_name, String hotel_loc, int hotel_image_id, String arrival_date, String departure_date) {
        this.booking_id = booking_id;
        this.user_id = user_id;
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotel_loc = hotel_loc;
        this.hotel_image_id = hotel_image_id;
        this.arrival_date = arrival_date;
        this.departure_date = departure_date;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_loc() {
        return hotel_loc;
    }

    public void setHotel_loc(String hotel_loc) {
        this.hotel_loc = hotel_loc;
    }

    public int getHotel_image_id() {
        return hotel_image_id;
    }

    public void setHotel_image_id(int hotel_image_id) {
        this.hotel_image_id = hotel_image_id;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }
}
