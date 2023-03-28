package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.ArrayList;

import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    List<Account> accountList;
    EditText edt_username;
    EditText edt_password;

    Button btn_login;

    public boolean isLoggedIn(List<Account> accList, String _username, String _password){
        for(Account acc : accList){
            if (acc.Equals(_username, _password)){
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountList = new ArrayList<>();
        accountList.add(new Account("isser", "123545"));
        accountList.add(new Account("ABC", "123456"));
        edt_username = (EditText)findViewById(R.id.username);
        edt_password = (EditText)findViewById(R.id.password);
        btn_login = (Button)findViewById(R.id.login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String _username = edt_username.getText().toString();
                String _password = edt_password.getText().toString();

                if (_username.equals("") || _password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Mời bạn nhập đầy đủ tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(isLoggedIn(accountList, _username, _password))
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Bạn đã nhập sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}