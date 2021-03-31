package com.example.bookspace.Output;

public abstract class OutputManager {
    private String URL = "http://localhost:8080/api";

    public String getURL() {
        return this.URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
    
}
