package com.example.myapplication;

public class ClassInfo {
    private String id;
    private String name;
    private String students;

    public String getID(){
        return id;
    }
    public void setID(){
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStudents(){
        return students;
    }
    public void setStudents(String students) {
        this.students = students;
    }

    public ClassInfo(){};
    public ClassInfo(String id, String name, String students){
        this.id = id;
        this.name = name;
        this.students = students;
    }
}
