package com.shubh.restraunt.dtos;

public class UserRegistrationResponse {

    private String jwt;
    private String username;
    private String password;

    public UserRegistrationResponse(String jwt, String username, String password) {
        this.jwt = jwt;
        this.username = username;
        this.password = password;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
