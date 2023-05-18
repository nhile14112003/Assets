package com.example.myapplication;

public class Account {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUername(String username) {
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Account(){};
    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }
}
