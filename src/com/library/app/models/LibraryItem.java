package com.library.app.models;

public class LibraryItem {
    private String name;
    private String creator;
    private String id;
    private String category;
    private String location;

    public LibraryItem(String name, String creator, String id, String category, String location) {
        this.name = name;
        this.creator = creator;
        this.id = id;
        this.category = category;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
