package com.example.bookspace.Output;

public abstract class OutputManager {
    private String URL = "https://bookspace-app.herokuapp.com//api";

    public String getURL() {
        return this.URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }


    
}
