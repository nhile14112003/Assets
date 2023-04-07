package com.example.bt02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        ListView listView = (ListView)findViewById(R.id.listViewSubjects);
        TextView tvName = (TextView) findViewById(R.id.name);
        TextView tvClass = (TextView) findViewById(R.id._class);
        TextView tvDate = (TextView) findViewById(R.id.date);
        ImageView imgView = (ImageView) findViewById(R.id.image);
        Bundle bundle = getIntent().getExtras();
        tvName.setText(bundle.getString("key_name"));
        tvDate.setText(bundle.getString("key_date"));
        tvClass.setText(bundle.getString("key_class"));
        imgView.setImageResource(bundle.getInt("key_image", 0));

        ArrayList<String> strings = bundle.getStringArrayList("key_subjects");
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings));

    }
}