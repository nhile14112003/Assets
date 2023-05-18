package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassAdapter extends BaseAdapter {
    private ArrayList<ClassInfo> classes;
    public ClassAdapter(ArrayList<ClassInfo> classes) {
        this.classes = classes;
    }
    @Override
    public int getCount() {
        return classes.size();
    }

    @Override
    public Object getItem(int position) {
        return classes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewItem;
        if (convertView == null) {
            viewItem = View.inflate(parent.getContext(), R.layout.adapter_class, null);
        }
        else viewItem = convertView;
        TextView tvName = (TextView) viewItem.findViewById(R.id.tv_name);
        TextView tvId = (TextView) viewItem.findViewById(R.id.tv_id);
        TextView tvStudents = (TextView) viewItem.findViewById(R.id.tv_students);
        tvName.setText("Name: " + classes.get(position).getName());
        tvId.setText("Id: " + classes.get(position).getID());
        tvStudents.setText("Students: " + classes.get(position).getStudents());
        return viewItem;
    }
}
