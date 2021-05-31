package com.example.medbay;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.medbay.Adapter.SpecAdapter;
import com.example.medbay.database.DBHelper;

import java.util.ArrayList;

public class BookApp extends AppCompatActivity {

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
        helper = new DBHelper(this);
        arrayList = new ArrayList<>();
        loadDataInListView();

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), BookDoc.class);
                int spec_id = position+1;
                i.putExtra("spec_id",spec_id);
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

}