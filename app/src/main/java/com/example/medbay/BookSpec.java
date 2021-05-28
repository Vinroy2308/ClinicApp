package com.example.medbay;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.medbay.database.DBHelper;

public class BookSpec extends AppCompatActivity {

    LinearLayout l;
    Button b;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_spec);
        l = findViewById(R.id.book_spec);
        int id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");

        helper = new DBHelper(this);
        Cursor c = helper.viewData(id);
        if(c != null) {
            c.moveToNext();
            b = new Button(this);
            b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            b.setText(""+c.getString(1));
            b.setId(c.getInt(0));
            l.addView(b);
        } else {
            Toast.makeText(this, "No Doctors available", Toast.LENGTH_SHORT).show();
        }

    }
}