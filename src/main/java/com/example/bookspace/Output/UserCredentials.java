package com.example.bookspace.Output;

public class UserCredentials {
    private Long id;
    private String token; 


    public UserCredentials() {
    }

    public UserCredentials(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
