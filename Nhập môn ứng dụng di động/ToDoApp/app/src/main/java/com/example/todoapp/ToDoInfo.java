package com.example.todoapp;

import androidx.annotation.NonNull;

import java.util.Date;

public class ToDoInfo {
    String ordinalNumber;
    String title;
    String description;
    String date;
    boolean isDone;
    public ToDoInfo(){}
    public ToDoInfo(String ordinalNumber, String title, String description, String date, boolean isDone){
        this.ordinalNumber = ordinalNumber;
        this.title = title;
        this.date = date;
        this.description = description;
        this.isDone = isDone;
    }

}
