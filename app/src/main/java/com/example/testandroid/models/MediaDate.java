package com.example.testandroid.models;

import java.io.Serializable;

public class MediaDate implements Serializable {

    private String dateString;
    private String year;

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
