package com.example.medbay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medbay.database.DBHelper;
import com.example.medbay.database.Medbay;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {
    Button login;
    TextView reg;
    EditText email, password;
    DBHelper help;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.sign1);
        email = findViewById(R.id.log_mail);
        password = findViewById(R.id.log_password);
        db = new DBHelper(this).getWritableDatabase();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();

                if (mail.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(Login.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                } else {
                    //                                Patient Login

                    Cursor c = db.rawQuery("select * from "+ Medbay.Patient.P_TABLE +" where "
                            +Medbay.Patient.P_EMAIL+"='"+mail+"' and "
                            +Medbay.Patient.P_PASSWORD+"='"+pass+"'",null);
                    if (c.getCount()>0) {
                        c.moveToNext();
                        int pid = c.getInt(0);
                        String name = c.getString(1);
                        Intent i = new Intent(getApplicationContext(),Home.class);
                        i.putExtra("pid",pid);
                        i.putExtra("name",name);
                        startActivity(i);
                        finish();
                    }else {

                    //                              Doctor Login

                        c = db.rawQuery("select * from "+Medbay.Doctor.D_TABLE+" where " +
                                Medbay.Doctor.D_NAME+"='"+mail+"' and "
                                + Medbay.Doctor.D_PASSWORD+"='"+pass+"'",null);
                        if (c.getCount() > 0) {
                            c.moveToNext();
                            int did = c.getInt(0);
                            String name = c.getString(1);
                            Intent i1 = new Intent(getApplicationContext(),DocHome.class);
                            i1.putExtra("did", did);
                            i1.putExtra("dname", name);
                            startActivity(i1);
                            finish();
                        } else {
//                        Invalid Credentials

                            Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Registration.class);
                startActivity(i);
            }
        });
    }


}