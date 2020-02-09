package com.example.transfinitte_decoders.adminDoc;

public class data {
    Boolean available;
    String title;

    public data(Boolean available, String title) {
        this.available = available;
        this.title = title;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public data() {
    }
}
