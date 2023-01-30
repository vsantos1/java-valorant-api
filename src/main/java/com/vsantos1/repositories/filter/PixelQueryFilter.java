package com.vsantos1.repositories.filter;

public class PixelQueryFilter {

    private final String title;
    private final String description;


    public PixelQueryFilter(String title, String description) {
        this.title = title;
        this.description = description;

    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


}
