package com.example.myapplication;

public class Student {
    private String name;
    private int image;
    private String id;
    private String dob;
    private String classID;
    public String getName(){
        return name;
    }
    public void setName(){
        this.name = name;
    }
    public String getID(){
        return id;
    }
    public void setID(){
        this.id = id;
    }
    public String getDob(){
        return dob;
    }
    public void setDob(){
        this.dob = dob;
    }
    public int getImage(){
        return image;
    }
    public void setImage(){
        this.image = image;
    }

    public Student(){}
    public Student(int image, String id, String name, String dob){
        this.name = name;
        this.image = image;
        this.dob = dob;
        this.id = id;
    }
}
