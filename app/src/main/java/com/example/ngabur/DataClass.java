package com.example.ngabur;

import java.io.Serializable;

public class DataClass implements Serializable {

    private String hotel_name;
    private String hotel_facility;
    private String hotel_bed;
    private String hotel_photo;
    private String hotel_rate;
    private String hotel_uid;
    private String hotel_breakfast;



    private String hotel_description;
    private String hotel_location;
    private int hotel_price;

    public String getHotel_description() {
        return hotel_description;
    }
    public String getHotel_rate() {
        return hotel_rate;
    }

    public String getHotel_breakfast() {
        return hotel_breakfast;
    }

    public int getHotel_price() {
        return hotel_price;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public String getHotel_facility() {
        return hotel_facility;
    }

    public String getHotel_bed() {
        return hotel_bed;
    }

    public String getHotel_photo() {
        return hotel_photo;
    }

    public String getHotel_location(){ return hotel_location; }

    public String getHotel_uid(){
        return hotel_uid;
    }

    public DataClass(){

    }

    public DataClass(String hotel_name, String hotel_facility, String hotel_bed, String hotel_photo, String hotel_rate, String hotel_breakfast, String hotel_location, String hotel_description, int hotel_price, String hotel_uid) {
        this.hotel_name = hotel_name;
        this.hotel_facility = hotel_facility;
        this.hotel_bed = hotel_bed;
        this.hotel_photo = hotel_photo;
        this.hotel_rate = hotel_rate;
        this.hotel_breakfast = hotel_breakfast;
        this.hotel_location = hotel_location;
        this.hotel_description = hotel_description;
        this.hotel_price = hotel_price;
        this.hotel_uid = hotel_uid;
    }
}
