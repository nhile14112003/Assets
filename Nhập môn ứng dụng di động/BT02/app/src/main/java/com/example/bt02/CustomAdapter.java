package com.example.bt02;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{
    private ArrayList<Student> items;
    customButtonListener customListner;

    public interface customButtonListener {
        public void onButtonClickListner(int position);
    }
    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }
    public CustomAdapter(ArrayList<Student> items) {

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
        TextView tvName = (TextView) viewItem.findViewById(R.id.name);
        TextView tvClass = (TextView) viewItem.findViewById(R.id._class);
        TextView tvDate = (TextView) viewItem.findViewById(R.id.date);
        ImageView imgView = (ImageView) viewItem.findViewById(R.id.image);
        tvName.setText(items.get(i).name);
        tvDate.setText(items.get(i).date);
        tvClass.setText(items.get(i)._class);
        imgView.setImageResource(items.get(i).image);
        viewItem.findViewById(R.id.more).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (customListner != null) {
                    customListner.onButtonClickListner(i);
                }
            }
        });
        return viewItem;
    }

}
