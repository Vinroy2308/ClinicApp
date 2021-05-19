package com.example.medbay.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "medbay";

    public DBHelper(Context context) {
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String patient = "create table "+ Medbay.Patient.P_TABLE+
                "(id integer PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                Medbay.Patient.P_NAME+" varchar(30), "+
                Medbay.Patient.P_EMAIL+" varchar(30), "+
                Medbay.Patient.P_DOB+" date, "+
                Medbay.Patient.P_PASSWORD+" varchar(30), "+
                Medbay.Patient.P_PHONE+" varchar(30) )";
        db.execSQL(patient);
    }

    public boolean addPat(String name, String email, String pass, String phone, String date) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Medbay.Patient.P_NAME,name);
        values.put(Medbay.Patient.P_EMAIL,email);
        values.put(Medbay.Patient.P_DOB,date);
        values.put(Medbay.Patient.P_PASSWORD,pass);
        values.put(Medbay.Patient.P_PHONE,phone);

        long in = db.insert(Medbay.Patient.P_TABLE,null, values);

        if(in > 0)
            return true;
        else
            return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
