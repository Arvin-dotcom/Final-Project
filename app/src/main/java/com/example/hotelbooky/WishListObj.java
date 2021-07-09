package com.example.hotelbooky;

import java.io.Serializable;

public class WishListObj implements Serializable {
    private int uid;
    private int fav_id;
    private String hotel_name;
    private String hotel_loc;
    private int hotel_img_id;

    public WishListObj(int uid, int fav_id, String hotel_name, String hotel_loc, int hotel_img_id) {
        this.uid = uid;
        this.fav_id = fav_id;
        this.hotel_name = hotel_name;
        this.hotel_loc = hotel_loc;
        this.hotel_img_id = hotel_img_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFav_id() {
        return fav_id;
    }

    public void setFav_id(int fav_id) {
        this.fav_id = fav_id;
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

    public int getHotel_img_id() {
        return hotel_img_id;
    }

    public void setHotel_img_id(int hotel_img_id) {
        this.hotel_img_id = hotel_img_id;
    }
}
