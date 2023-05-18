package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "classManangement";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table ACCOUNT(username text primary key, password text)");
        db.execSQL("create table CLASS(id text primary key, name text, students text)");
        db.execSQL("create table STUDENT(id text primary key, image integer, name text, dob text, classID text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists ACCOUNT");
        db.execSQL("drop table if exists CLASS");
        db.execSQL("drop table if exists STUDENT");
        onCreate(db);
    }
    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", account.getUsername());
        values.put("password", account.getPassword());

        db.insert("ACCOUNT", null, values);
        db.close();
    }
    public void addClass(ClassInfo classinfo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", classinfo.getID());
        values.put("name", classinfo.getName());
        values.put("students", classinfo.getStudents());

        db.insert("CLASS", null, values);
        db.close();
    }
    public void addStudent(String id, String name, String dob, int image, String classID) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("image", image);
        values.put("name", name);
        values.put("dob", dob);
        values.put("classID", classID);

        db.insert("STUDENT", null, values);
        db.close();
    }
    public Account getAccount(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Account account = null;
        String selection = "select * from ACCOUNT where username = ? and password = ?";
        String []selectionArgs = {username, password};
        Cursor cursor = db.rawQuery(selection, selectionArgs);
        if(cursor.moveToFirst()) {
            account = new Account(cursor.getString(0), cursor.getString(1));
            return account;
        }
        return account;
    }
    public ArrayList<ClassInfo> getClasses() {
        SQLiteDatabase db = this.getReadableDatabase();;
        ArrayList<ClassInfo> classes = new ArrayList<>();// cannot asssign null because it is boolean(default)
        String selection = "select * from CLASS";
        Cursor cursor = db.rawQuery(selection, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                classes.add(new ClassInfo(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
                cursor.moveToNext();
            }
        }
        return classes;
    }
    public ArrayList<Student> getStudents(String classID) {
        SQLiteDatabase db = this.getReadableDatabase();;
        ArrayList<Student> students = new ArrayList<>();// cannot asssign null because it is boolean(default)
        String selection = "select * from STUDENT where classID = ?";
        String []selectionArgs = {classID};
        Cursor cursor = db.rawQuery(selection, selectionArgs);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                students.add(new Student(cursor.getInt(1), cursor.getString(0), cursor.getString(2), cursor.getString(3)));
                cursor.moveToNext();
            }
        }
        return students;
    }
}
