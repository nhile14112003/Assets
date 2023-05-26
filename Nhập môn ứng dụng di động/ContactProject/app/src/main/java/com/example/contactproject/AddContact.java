package com.example.contactproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddContact extends AppCompatActivity {
    Button btnSave;
    EditText edtName, edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, PackageManager.PERMISSION_GRANTED);

        btnSave = findViewById(R.id.save);
        edtName = findViewById(R.id.name);
        edtPhone = findViewById(R.id.phone);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Insert name and phone
                ArrayList<ContentProviderOperation> contentProviderOperations
                        = new ArrayList<ContentProviderOperation>();
                contentProviderOperations.add(ContentProviderOperation.newInsert(
                                ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                        .build());
                // Adding Name
                contentProviderOperations.add(ContentProviderOperation
                        .newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                                edtName.getText().toString())
                        .build());
                // Adding Phone
                contentProviderOperations.add(ContentProviderOperation
                        .newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,
                                edtPhone.getText().toString())
                        .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                        .build());

                try {
                    getContentResolver().applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(AddContact.this, MainActivity.class));
            }
        });
    }
}