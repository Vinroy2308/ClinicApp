package com.example.medbay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medbay.Adapter.DocAdapter;

import com.example.medbay.Adapter.SpecAdapter;
import com.example.medbay.database.DBHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BookDoc extends AppCompatActivity {

    int id;
    String doc_name;
    EditText datePick, timePick;
    int year;
    int day;
    int month;

    DatePickerDialog.OnDateSetListener setListener;

    ListView l;
    DBHelper helper;
    ArrayList<Doctor2> arrayList;
    DocAdapter myAdapter;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_doc);
        helper = new DBHelper(this);
        arrayList = new ArrayList<>();
        l = findViewById(R.id.docList);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        id = getIntent().getIntExtra("spec_id",0);

        loadDocInListView();


        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txt = (TextView) view.findViewById(R.id.spec_name_txt);
                doc_name = txt.getText().toString();
                createNewScheduleDialog();

            }
        });

    }

    public void createNewScheduleDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View schedulePopUp = getLayoutInflater().inflate(R.layout.popup,null);
        schedule = schedulePopUp.findViewById(R.id.schedule_txt);
        datePick = schedulePopUp.findViewById(R.id.datePick);
        timePick = schedulePopUp.findViewById(R.id.timePick);
        dialogBuilder.setView(schedulePopUp);
        dialog = dialogBuilder.create();
        dialog.show();
        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        schedulePopUp.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = year+"-"+month+"-"+day;
                        datePick.setText(date);
                    }
                },year,month,day
                );
                datePickerDialog.show();
            }
        });

    }


    private void loadDocInListView() {
        arrayList = helper.getDocData(id);
        myAdapter = new DocAdapter(this,arrayList);
        l.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
}