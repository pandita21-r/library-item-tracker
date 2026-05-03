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

    @Override
    public String toString() {
        return "Item: " + name + " | ID: " + id;
    }
}
