package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    private ArrayList<Student> students;

    public StudentAdapter(ArrayList<Student> students) {
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewItem;
        if (convertView == null) {
            viewItem = View.inflate(parent.getContext(), R.layout.adapter_student, null);
        }
        else viewItem = convertView;
        TextView tvName = (TextView) viewItem.findViewById(R.id.tv_name);
        TextView tvId = (TextView) viewItem.findViewById(R.id.tv_id);
        TextView tvDob = (TextView) viewItem.findViewById(R.id.tv_dob);
        ImageView imgView = (ImageView) viewItem.findViewById(R.id.image);
        tvName.setText("Name: " + students.get(position).getName());
        tvId.setText("Id: " + students.get(position).getID());
        tvDob.setText("Dob: " + students.get(position).getDob());
        imgView.setImageResource(students.get(position).getImage());
        return viewItem;
    }
}
