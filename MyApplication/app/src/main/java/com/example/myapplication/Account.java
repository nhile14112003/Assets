package com.example.myapplication;

public class Account {
    private String username;
    private String password;

    public Account(){};
    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean Equals(String username, String password){
        if (this.username.equals(username) && this.password.equals(password))
            return true;
        return false;
    }
}
