package com.example.medbay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    TextView welcome;
    Button book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        welcome = findViewById(R.id.wel);
        book = findViewById(R.id.bkappoint);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk = new Intent(getApplicationContext(), BookApp.class);
                startActivity(bk);
            }
        });
    }
}