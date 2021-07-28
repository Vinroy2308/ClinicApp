package com.example.medbay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.medbay.Adapter.DocAdapter;

import com.example.medbay.Adapter.SpecAdapter;
import com.example.medbay.database.DBHelper;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BookDoc extends AppCompatActivity {

    int id,pid,did;
    String doc_name;
    TextView datePick, timePick;
    int year;
    int day;
    int month;
    int tHour, tMinute;

    DatePickerDialog.OnDateSetListener setListener;

    ListView l;
    DBHelper helper;
    ArrayList<Doctor2> arrayList;
    DocAdapter myAdapter;
    Button book, cancel;
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
        pid = getIntent().getIntExtra("p_id",0);

        loadDocInListView();


        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txt = (TextView) view.findViewById(R.id.spec_name_txt);
                TextView t_id = (TextView) view.findViewById(R.id.spec_id_txt);
                did = Integer.parseInt(t_id.getText().toString());
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
                        String date = year+"-"+month+"-"+dayOfMonth;
                        datePick.setText(date);
                    }
                },year,month,day
                );
                datePickerDialog.show();
            }
        });

        timePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        schedulePopUp.getContext(), android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                tHour = hourOfDay;
                                tMinute = minute;

                                String time = tHour+":"+tMinute;

                                SimpleDateFormat fhours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = fhours.parse(time);

                                    SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");

                                    timePick.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                timePickerDialog.updateTime(tHour,tMinute);
                timePickerDialog.show();
            }
        });
        book = schedulePopUp.findViewById(R.id.book_appoint);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bk_date = datePick.getText().toString();
                String bk_time = timePick.getText().toString();
                Boolean i = helper.addAppoint(bk_date,bk_time,pid,did);
                if (i) {
                    Toast.makeText(BookDoc.this, "Appointment Scheduled Succesfully", Toast.LENGTH_SHORT).show();
                    Intent go = new Intent(getApplicationContext(),Home.class);
                    go.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    go.putExtra("pid",pid);
                    startActivity(go);
                }
            }
        });

    }


    private void loadDocInListView() {
        arrayList = helper.getDocData(id);
        myAdapter = new DocAdapter(this,arrayList);
        l.setAdapter(myAdapter);
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