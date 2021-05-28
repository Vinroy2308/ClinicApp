package com.example.medbay;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.medbay.database.DBHelper;

public class BookApp extends AppCompatActivity {

    Button b;
    LinearLayout book;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_app);
        book = findViewById(R.id.bookapp);
        helper = new DBHelper(this);
        Cursor c = helper.viewDoc();

        for (int i = 0; i < c.getCount(); i++) {
            b = new Button(this);
            c.moveToNext();
            b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            b.setText(""+c.getString(1));
            b.setId(i+1);
            b.setBackgroundColor(Color.TRANSPARENT);
            book.addView(b);
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = b.getId();
                String name = b.getText().toString();
                Intent i = new Intent(getApplicationContext(), BookSpec.class);
                i.putExtra("id", id);
                i.putExtra("name",name);
                startActivity(i);
            }
        });

    }
}