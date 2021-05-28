package com.example.medbay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.medbay.database.DBHelper;

import java.util.ArrayList;

public class BookApp extends AppCompatActivity {

    RecyclerView recyclerview;
    LinearLayout book;
    DBHelper helper;
    ArrayList<String> spec_id, spec;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_app);
        book = findViewById(R.id.bookapp);
        helper = new DBHelper(this);
        spec_id = new ArrayList<>();
        spec = new ArrayList<>();

        readData();


        customAdapter = new CustomAdapter(this, spec_id, spec);
        recyclerview.setAdapter(customAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

    }

    void readData() {
        Cursor cursor = helper.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                spec_id.add(cursor.getString(0));
                spec.add(cursor.getString(1));
            }
        }
    }
}