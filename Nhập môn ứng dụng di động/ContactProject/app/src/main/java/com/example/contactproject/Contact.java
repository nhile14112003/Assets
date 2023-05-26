package com.example.contactproject;

public class Contact {
    private String name;
    private String phone;
    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public void setName(){
        this.name = name;
    }
    public void setPhone(){
        this.phone = phone;
    }
    public Contact(){}
    public Contact(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

}
