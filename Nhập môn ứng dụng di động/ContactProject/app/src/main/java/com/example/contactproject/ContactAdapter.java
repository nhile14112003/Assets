package com.example.contactproject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    private ArrayList<Contact> contacts;
    public ContactAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewItem;
        if (convertView == null) {
            viewItem = View.inflate(parent.getContext(), R.layout.adapter_contact, null);
        }
        else viewItem = convertView;
        TextView tvName = (TextView) viewItem.findViewById(R.id.name);
        TextView tvPhoneNumber = (TextView) viewItem.findViewById(R.id.phoneNumber);
        tvName.setText(contacts.get(position).getName());
        tvPhoneNumber.setText(contacts.get(position).getPhone());
        return viewItem;
    }
}
