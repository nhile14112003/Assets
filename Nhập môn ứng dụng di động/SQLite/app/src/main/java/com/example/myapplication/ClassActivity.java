package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ClassActivity extends AppCompatActivity {
    private ArrayList<ClassInfo> classList = new ArrayList<>();

    ListView lstview;
    ClassAdapter adapter;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        db = new DatabaseHandler(ClassActivity.this);
        classList.addAll(db.getClasses());
        lstview = (ListView) findViewById(R.id.listViewClasses);
        adapter = new ClassAdapter(classList);
        lstview.setAdapter(adapter);

        lstview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassInfo classInfo = (ClassInfo) classList.get(position);
                Intent intent = new Intent(ClassActivity.this, ClassDetailActivity.class);
                intent.putExtra("id", classInfo.getID());
                intent.putExtra("name", classInfo.getName());
                intent.putExtra("students", classInfo.getStudents());
                startActivity(intent);
            }
        });
    }
}