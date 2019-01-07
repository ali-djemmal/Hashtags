package com.example.alilo.hashtags;

import java.util.ArrayList;

public class hashTag {
    private String name;
    private String image;
private ArrayList<tag> tags ;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<tag> tags) {
        this.tags = tags;
    }
}
