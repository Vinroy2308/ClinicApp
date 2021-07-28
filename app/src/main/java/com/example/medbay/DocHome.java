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
import android.widget.ListView;
import android.widget.TextView;

import com.example.medbay.Adapter.PatAdapter;
import com.example.medbay.Adapter.SpecAdapter;
import com.example.medbay.database.DBHelper;

import java.util.ArrayList;

public class DocHome extends AppCompatActivity {

    int did;
    String dname;
    TextView tv;

    DBHelper helper;
    ListView l1;
    ArrayList<PatientMod> arrayList;
    PatAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_home);
        l1 = findViewById(R.id.patient_list);
        helper = new DBHelper(this);
        tv = findViewById(R.id.textView5);
        arrayList = new ArrayList<>();
        did = getIntent().getIntExtra("did",0);
        dname = getIntent().getStringExtra("dname");
        tv.setText("Welcome DR."+dname);

        loadPatientInListView();

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i =new Intent(getApplicationContext(), DocPatient.class);
                i.putExtra("did",did);
                TextView tv = (TextView)view.findViewById(R.id.p_id);
                TextView name = (TextView)view.findViewById(R.id.pat_name);
                int pid = Integer.parseInt(tv.getText().toString());
                i.putExtra("pid",pid);
                i.putExtra("pname",name.getText().toString());
                startActivity(i);

            }
        });
    }

    private void loadPatientInListView() {
        arrayList = helper.getPatData(did);
        myAdapter = new PatAdapter(this,arrayList);
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