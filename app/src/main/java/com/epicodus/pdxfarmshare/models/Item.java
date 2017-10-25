package com.epicodus.pdxfarmshare.models;

/**
 * Created by rev.j.p.rinehart on 10/25/2017.
 */

public class Item {
    private int id;
    private int userId;
    private String name;
    private String location;
    private boolean pub = false;
    private String description;
    private boolean barter = false;

    private String type;

    public Item (int userId, String name, String location, boolean pub, String description, boolean barter){
        this.userId = userId;
        this.name = name;
        this.location = location;
        this.pub = pub;
        this.description = description;
        this.barter = barter;
    }

    public Item (String name, String location, boolean pub, String description){
        this.name = name;
        this.location = location;
        this.pub = pub;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
