package com.example.bt02;

import java.util.ArrayList;

public class Student {
    String name;
    int image;
    String date;
    String _class;
    ArrayList<String> subjects;
    public Student(){}
    public Student(String name, int image, String date, String _class, ArrayList<String> subjects){
        this.name = name;
        this.image = image;
        this.date = date;
        this._class =_class;
        this.subjects = subjects;
    }
}
