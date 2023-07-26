package com.example.movieapp.ui.actors;

public class Actor {

    //Model class for actors
    private String name;

    public int getId() {
        return id;
    }

    private int id;
    private String profile_path;

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    private Boolean isSelected;

    public Actor(String name, String profile_path) {
        this.name = name;
        this.profile_path = profile_path;
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return profile_path;
    }



}
