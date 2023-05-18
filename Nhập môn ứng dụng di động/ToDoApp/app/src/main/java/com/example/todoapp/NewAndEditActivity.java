package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class NewAndEditActivity extends AppCompatActivity {
    Button btSave;
    EditText edtTitle;
    EditText edtDescp;
    EditText edtDate;
    CheckBox ckIsDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_and_edit);
        btSave = (Button) findViewById(R.id.save);
        edtTitle = (EditText) findViewById(R.id.title);
        edtDescp = (EditText) findViewById(R.id.descp);
        edtDate = (EditText) findViewById(R.id.date);
        ckIsDone = (CheckBox) findViewById(R.id.isDone);
        Intent data = getIntent();
        if (data.hasExtra("index")) {
            edtTitle.setText(data.getStringExtra("title"));
            edtDescp.setText(data.getStringExtra("descp"));
            edtDate.setText(data.getStringExtra("date"));
            ckIsDone.setChecked(data.getBooleanExtra("isDone", false));
        }
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewAndEditActivity.this, MainActivity.class);
                intent.putExtra("title", edtTitle.getText().toString());
                intent.putExtra("descp", edtDescp.getText().toString());
                intent.putExtra("date", edtDate.getText().toString());
                intent.putExtra("isDone", ckIsDone.isChecked());
                if (data.hasExtra("index")) {
                    intent.putExtra("index", data.getIntExtra("index", 0));
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(NewAndEditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}