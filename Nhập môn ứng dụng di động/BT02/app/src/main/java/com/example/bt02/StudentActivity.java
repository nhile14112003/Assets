package com.example.bt02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import com.example.bt02.CustomAdapter.customButtonListener;

public class StudentActivity extends AppCompatActivity implements customButtonListener {
    private ListView lstview;
    CustomAdapter adapter;
    private ArrayList<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        studentList = new ArrayList<>();
        studentList.add(new Student("Le Thi Lan Nhi", R.drawable.image_account, "14/11/2003", "KTPM2021", new ArrayList<>(Arrays.asList("IT001", "IT002", "MA004", "SS007", "ENG01"))));
        studentList.add(new Student("Nguyen Thao My", R.drawable.account2, "09/07/2004", "KHMT2022", new ArrayList<>(Arrays.asList("IT004", "IT005", "MA005", "SE005", "ENG02"))));
        lstview = (ListView) findViewById(R.id.listViewStudent);
        adapter = new CustomAdapter(studentList);
        adapter.setCustomButtonListner(StudentActivity.this);
        lstview.setAdapter(adapter);

    }

    public void onButtonClickListner(int position) {
        Intent intent = new Intent(StudentActivity.this, StudentDetail.class);
        Student student = studentList.get(position);
        intent.putExtra("key_name", student.name);
        intent.putExtra("key_image", student.image);
        intent.putExtra("key_date", student.date);
        intent.putExtra("key_class", student._class);
        intent.putExtra("key_subjects", student.subjects);
        startActivity(intent);
    }
}