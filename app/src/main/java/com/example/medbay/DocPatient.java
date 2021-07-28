package com.example.medbay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medbay.Adapter.DocPatientAdapter;
import com.example.medbay.Adapter.DocPatientModel;
import com.example.medbay.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DocPatient extends AppCompatActivity {
    int did, pid;
    TextView patient_name;
    Button enter,show;
    EditText med, dos, day;


    ListView li;
    ArrayList<DocPatientModel> arrayList;
    DocPatientAdapter adapter;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_patient);
        did = getIntent().getIntExtra("did",0);
        pid = getIntent().getIntExtra("pid",0);
        helper = new DBHelper(this);
        patient_name = findViewById(R.id.name);
        patient_name.setText(getIntent().getStringExtra("pname"));
        med = findViewById(R.id.medicine);
        dos = findViewById(R.id.dosage);
        day = findViewById(R.id.days);
        enter = findViewById(R.id.enter);
        show = findViewById(R.id.show);
        li = findViewById(R.id.med_list);
        arrayList = new ArrayList<>();
        loadMedList();

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medi, dosa;
                medi = med.getText().toString();
                dosa = dos.getText().toString();
                int d = Integer.parseInt(day.getText().toString());

                int mid = helper.addMed(medi);
                boolean flag = helper.addPresc(pid,mid,medi,dosa,d);
                if(flag) {
                    Toast.makeText(DocPatient.this, "Prescription inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DocPatient.this, "Prescription not inserted", Toast.LENGTH_SHORT).show();
                }


            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMedList();
            }
        });


    }

    private void loadMedList() {
        arrayList = helper.getPatPresc(pid);
        adapter = new DocPatientAdapter(this, arrayList);
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