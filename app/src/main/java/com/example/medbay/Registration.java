package com.example.medbay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.medbay.database.DBHelper;

import java.net.Inet4Address;

public class  Registration extends AppCompatActivity {
    Button sign;
    CheckBox doc;
    EditText name, email, dob, password, phone;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        // Initialize
        doc = findViewById(R.id.do_check);
        name = findViewById(R.id.name);
        email =findViewById(R.id.email);
        dob = findViewById(R.id.dob);
        password = findViewById(R.id.reg_password);
        phone = findViewById(R.id.phone);
        sign = findViewById(R.id.sign2);

        helper = new DBHelper(this);

        // ON Button click
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                String uname = name.getText().toString();
                String mail = email.getText().toString();
                String date = dob.getText().toString();
                String pass = password.getText().toString();
                String mob = phone.getText().toString();

                if(doc.isChecked()) {                                        // If doctor checked
                    i = new Intent(getApplicationContext(),DocHome.class);
                } else {// If Patient

                    boolean status = helper.addPat(uname,mail,pass,mob,date);
                    if(status) {
                        Toast.makeText(Registration.this, "Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Registration.this, "Not  inserted", Toast.LENGTH_SHORT).show();
                    }
                    i = new Intent(getApplicationContext(),Home.class);
                }
                i.putExtra("name",uname);
                startActivity(i);
            }
        });

    }
}