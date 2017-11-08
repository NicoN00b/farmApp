package com.epicodus.pdxfarmshare.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Item {

    String name;
    String location;
    String pub;
    String description;
    String barter;
    String type;
    String pushId;


    String favorite;

    public Item() {}

    public Item (String name, String location, String description, String pub, String barter){
        this.name = name;
        this.location = location;
        this.description = description;
        this.pub = pub;
        this.barter = barter;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public String getBarter() {
        return barter;
    }

    public void setBarter(String barter) {
        this.barter = barter;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }


    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
