package com.example.todoapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<ToDoInfo> items;

    public CustomAdapter(ArrayList<ToDoInfo> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem;
        if (view == null) {
            viewItem = View.inflate(viewGroup.getContext(), R.layout.custom_adapter, null);
        }
        else viewItem = view;
        TextView tvOrdinalNumber = (TextView) viewItem.findViewById(R.id.ordinalNumber);
        TextView tvTitle = (TextView) viewItem.findViewById(R.id.title);
        TextView tvDescp = (TextView) viewItem.findViewById(R.id.descp);
        TextView tvDate = (TextView) viewItem.findViewById(R.id.date);
        tvOrdinalNumber.setText(items.get(i).ordinalNumber);
        tvTitle.setText(items.get(i).title);
        tvDescp.setText(items.get(i).description);
        tvDate.setText(items.get(i).date);
        CheckBox cbIsDone = (CheckBox) viewItem.findViewById(R.id.isDone);
        cbIsDone.setChecked(items.get(i).isDone);
        cbIsDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.get(i).isDone = !items.get(i).isDone;
                cbIsDone.setChecked(items.get(i).isDone);
            }
        });
        return viewItem;
    }

}
