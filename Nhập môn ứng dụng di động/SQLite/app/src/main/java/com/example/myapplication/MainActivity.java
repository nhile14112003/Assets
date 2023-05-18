package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt_username;
    EditText edt_password;
    Button btn_login;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_username = (EditText)findViewById(R.id.username);
        edt_password = (EditText)findViewById(R.id.password);
        btn_login = (Button)findViewById(R.id.login);
        db = new DatabaseHandler(MainActivity.this);
        db.addAccount(new Account("lannhi", "141123"));
        db.addAccount(new Account("hami", "090722"));

        db.addClass(new ClassInfo("class01", "Class001", "8"));
        db.addClass(new ClassInfo("class02", "Class002", "6"));
        db.addClass(new ClassInfo("class03", "Class003", "7"));
        db.addClass(new ClassInfo("class04", "Class004", "12"));

        db.addStudent("student001", "Nguyen Van A", "01/01/2000", R.drawable.image_account, "class01");
        db.addStudent("student002", "Tran Thi B", "01/02/2000", R.drawable.image_account, "class01");
        db.addStudent("student003", "Dao Van Loc", "01/03/2000", R.drawable.image_account, "class01");
        db.addStudent("student004", "Ngo Thi Linh", "12/01/2000", R.drawable.image_account, "class01");
        db.addStudent("student005", "Ngo Thi Thao", "12/01/2001", R.drawable.image_account, "class01");
        db.addStudent("student006", "Le Xuan Anh", "12/02/2003", R.drawable.image_account, "class01");
        db.addStudent("student007", "Le Xuan Tu", "12/02/2000", R.drawable.image_account, "class01");
        db.addStudent("student008", "Le Son", "12/02/2004", R.drawable.image_account, "class01");
        db.addStudent("student009", "Le Xuan Huy", "12/03/2000", R.drawable.image_account, "class02");
        db.addStudent("student010", "Le Xuan Truong", "11/05/2002", R.drawable.image_account, "class02");
        db.addStudent("student011", "Le Xuan Duc", "12/11/1999", R.drawable.image_account, "class02");
        db.addStudent("student012", "Le Thi Mai", "12/05/2001", R.drawable.image_account, "class02");
        db.addStudent("student013", "Truong Bich", "12/03/2002", R.drawable.image_account, "class02");
        db.addStudent("student014", "Le Hoai", "08/03/2000", R.drawable.image_account, "class02");
        db.addStudent("student015", "Le Bao Nhu", "09/03/2000", R.drawable.image_account, "class03");
        db.addStudent("student016", "Le Thi Lan", "10/03/2000", R.drawable.image_account, "class03");
        db.addStudent("student017", "Nguyen Cat Tu", "08/04/2000", R.drawable.image_account, "class03");
        db.addStudent("student018", "Nguyen An", "09/12/2000", R.drawable.image_account, "class03");
        db.addStudent("student019", "Le Duc Hau", "20/03/2000", R.drawable.image_account, "class03");
        db.addStudent("student020", "Pham Le Quy Anh", "28/02/2000", R.drawable.image_account, "class03");
        db.addStudent("student021", "Pham Anh Duc", "08/09/2000", R.drawable.image_account, "class03");
        db.addStudent("student022", "Le Pham Hai", "08/08/2000", R.drawable.image_account, "class04");
        db.addStudent("student023", "Dinh Nguyen Loc", "08/06/2001", R.drawable.image_account, "class04");
        db.addStudent("student024", "Ha Mien", "10/07/2002", R.drawable.image_account, "class04");
        db.addStudent("student025", "Le Van D", "10/07/2000", R.drawable.image_account, "class04");
        db.addStudent("student026", "Luong Le", "08/03/2000", R.drawable.image_account, "class04");
        db.addStudent("student027", "Nguyen Minh Duy", "14/02/2000", R.drawable.image_account, "class04");
        db.addStudent("student028", "Luong Long Vu", "25/04/1998", R.drawable.image_account, "class04");
        db.addStudent("student029", "Tran Doan Nhat", "30/04/2000", R.drawable.image_account, "class04");
        db.addStudent("student030", "Tran Y Vy", "29/05/1999", R.drawable.image_account, "class04");
        db.addStudent("student031", "Tran Yen Nhi", "21/10/2003", R.drawable.image_account, "class04");
        db.addStudent("student032", "Le Yen Ngoc", "22/10/2000", R.drawable.image_account, "class04");
        db.addStudent("student033", "Nguyen Tran Ngo", "08/07/2000", R.drawable.image_account, "class04");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String _username = edt_username.getText().toString();
                String _password = edt_password.getText().toString();

                if (_username.trim().equals("") || _password.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Mời bạn nhập đầy đủ tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                }
                else{
                    Account account = db.getAccount(_username, _password);
                    if(account != null) {
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, ClassActivity.class));
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Bạn đã nhập sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}