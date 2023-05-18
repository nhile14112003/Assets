package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassDetailActivity extends AppCompatActivity {

    private ArrayList<Student> students = new ArrayList<>();
    ListView lstview;
    StudentAdapter adapter;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);
        TextView tvId = (TextView) findViewById(R.id.tv_id);
        TextView tvName = (TextView) findViewById(R.id.tv_name);
        TextView tvStudents = (TextView) findViewById(R.id.tv_students);
        db = new DatabaseHandler(ClassDetailActivity.this);
        Bundle bundle = getIntent().getExtras();
        tvId.setText("Id: " + bundle.getString("id"));
        tvName.setText("Name: " + bundle.getString("name"));
        tvStudents.setText("Students: " + bundle.getString("students"));
        lstview = (ListView) findViewById(R.id.listViewStudents);
        students.addAll(db.getStudents(bundle.getString("id")));
        adapter = new StudentAdapter(students);
        lstview.setAdapter(adapter);
    }
}