package com.example.hotelbooky;

import java.io.Serializable;

public class HotelObj implements Serializable {
    private int hotel_id;
    private String hotel_name;
    private String location;
    private String rates;
    private int image_id;

    public HotelObj(int hotel_id, String hotel_name, String location, String rates, int image_id) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.location = location;
        this.rates = rates;
        this.image_id = image_id;
    }

    @Override
    public String toString() {
        return "HotelObj{" +
                "hotel_id=" + hotel_id +
                ", hotel_name='" + hotel_name + '\'' +
                ", location='" + location + '\'' +
                ", rates='" + rates + '\'' +
                ", image_id=" + image_id +
                '}';
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }
}
