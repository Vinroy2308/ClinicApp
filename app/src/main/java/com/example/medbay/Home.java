package com.example.medbay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.medbay.database.DBHelper;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    TextView welcome;
    Button book;
    int pid;
    
    ListView li,l2;
    ArrayList<AppointModel> arrayList;
    AppointAdapter adapter;

    ArrayList<PrescModel> presList;
    PrescAdapter model;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        welcome = findViewById(R.id.wel);
        book = findViewById(R.id.bkappoint);
        helper = new DBHelper(this);
        li = findViewById(R.id.pat_home_list);
        l2 = findViewById(R.id.presc_home_list);
        presList = new ArrayList<>();




        String name = getIntent().getStringExtra("name");
        welcome.setText("Welcome "+ name);

        //
        pid = getIntent().getIntExtra("pid",0);


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk = new Intent(getApplicationContext(), BookApp.class);
                bk.putExtra("pid",pid);
                startActivity(bk);
            }
        });

        loadAppointList();
        loadPrescList();
    }

    private void loadPrescList() {
        presList = helper.getPresc(pid);
        model = new PrescAdapter(this,presList);
        l2.setAdapter(model);
        model.notifyDataSetChanged();
    }

    private void loadAppointList() {
        arrayList = helper.getAppData(pid);
        adapter = new AppointAdapter(this, arrayList);
        li.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            Intent i = new Intent(getApplicationContext(), Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
            return true;
        }
        return false;
    }
}