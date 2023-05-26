package com.example.contactproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private ArrayList<Contact> contactList;
    public static int CONTACT_LOADER = 0;
    public static boolean isASC = true;

    ListView lstview;
    ContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactList = new ArrayList<>();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PackageManager.PERMISSION_GRANTED);// lay Manifest cua android

        LoaderManager.getInstance(this).restartLoader(this.CONTACT_LOADER,null,this);

        lstview = (ListView) findViewById(R.id.listViewContact);
        adapter = new ContactAdapter(contactList);
        onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.optionNew:
                startActivity(new Intent(MainActivity.this, AddContact.class));
                return true;
            case R.id.optionAsc:
                isASC = true;
                recreate();
                return true;
            case R.id.optionDesc:
                isASC = false;
                recreate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == CONTACT_LOADER) {
            String[] SELECTED_FIELDS = new String[]
                    {
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER,
                    };
            return new CursorLoader(this, ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    SELECTED_FIELDS,
                    null,
                    null,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " " + (isASC ? "ASC" : "DESC"));
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (loader.getId() == CONTACT_LOADER) {
            ArrayList<Contact> contacts = new ArrayList<>();
            if (data != null) {
                while (!data.isClosed() && data.moveToNext()) {
                    String name = data.getString(1);
                    String phone = data.getString(2);
                    contacts.add(new Contact(name, phone));
                }
                contactList.clear();
                contactList.addAll(contacts);
                lstview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                data.close();
            }
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        loader = null;
    }

}