package com.epicodus.pdxfarmshare.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Item {

     String name;
     String location;
     boolean pub = false;
     String description;
     boolean barter = false;
     String type;
     private String pushId;

    public Item() {}

    public Item (String name, String location, boolean pub, String description, boolean barter){
        this.name = name;
        this.location = location;
        this.pub = pub;
        this.description = description;
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

    public boolean isPub() {
        return pub;
    }

    public void setPub(boolean pub) {
        this.pub = pub;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBarter() {
        return barter;
    }

    public void setBarter(boolean barter) {
        this.barter = barter;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
