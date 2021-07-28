package com.example.medbay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.medbay.Adapter.SpecAdapter;
import com.example.medbay.database.DBHelper;

import java.util.ArrayList;

public class BookApp extends AppCompatActivity {
    int pid;

    LinearLayout book;
    DBHelper helper;
    ListView l1;
    ArrayList<Doctor1> arrayList;
    SpecAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_app);
        book = findViewById(R.id.bookapp);
        l1 = findViewById(R.id.listView);
        pid = getIntent().getIntExtra("pid",0);
        helper = new DBHelper(this);
        arrayList = new ArrayList<>();

        loadDataInListView();

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), BookDoc.class);
                int spec_id = position+1;
                i.putExtra("spec_id",spec_id);
                i.putExtra("p_id",pid);
                startActivity(i);
            }
        });

    }

    private void loadDataInListView() {
        arrayList = helper.getAllData();
        myAdapter = new SpecAdapter(this,arrayList);
        l1.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
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