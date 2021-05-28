package com.example.medbay.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;
import android.widget.Toast;

import com.example.medbay.Registration;


public class DBHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "medbay";
    public static int DB_VERSION = 5;

    public static final String patient = "create table if not exists "+ Medbay.Patient.P_TABLE+
            "(id integer PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            Medbay.Patient.P_NAME+" varchar(30), "+
            Medbay.Patient.P_EMAIL+" varchar(30), "+
            Medbay.Patient.P_DOB+" date, "+
            Medbay.Patient.P_PASSWORD+" varchar(30), "+
            Medbay.Patient.P_PHONE+" varchar(30) )";


    public static final String special = "create table if not exists "+ Medbay.Special.S_TABLE+
            "(sid integer PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            Medbay.Special.S_NAME+" varchar(30) )";


    public static final String doctor = "create table if not exists " + Medbay.Doctor.D_TABLE +
            "( did integer PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            Medbay.Doctor.D_NAME+" varchar(30), "+
            Medbay.Doctor.D_EMAIL+" varchar(30), "+
            Medbay.Doctor.D_PASSWORD+" varchar(30), "+
            Medbay.Doctor.D_PHONE+" varchar(30), "+
            Medbay.Doctor.DS_ID+" integer, FOREIGN KEY ("+Medbay.Doctor.DS_ID+") REFERENCES "+Medbay.Special.S_TABLE+"(sid) )";

    public static final String appoint = "create table if not exists "+ Medbay.Appoint.A_TABLE +
            "("+Medbay.Appoint.A_PID+" integer NOT NULL, "+
            Medbay.Appoint.A_DID+" integer NOT NULL, "+
            Medbay.Appoint.A_DATE+" date, "+
            Medbay.Appoint.A_TIME+" time, " +
            "PRIMARY KEY ("+Medbay.Appoint.A_PID+","+Medbay.Appoint.A_DID+")," +
            "FOREIGN KEY ("+Medbay.Appoint.A_PID+") REFERENCES "+Medbay.Patient.P_TABLE+"(id) on delete cascade on update cascade," +
            "FOREIGN KEY ("+Medbay.Appoint.A_DID+") REFERENCES "+Medbay.Doctor.D_TABLE+"(did) on delete cascade on update cascade )" ;

    public static final String med = "create table if not exists "+Medbay.Medicine.M_TABLE+
            "(mid integer PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            Medbay.Medicine.M_NAME+" varchar(30) )";

    public static final String presc = "create table if not exists "+ Medbay.Presc.P_TABLE +
            "( "+Medbay.Presc.P_PID+" integer, "+
            Medbay.Presc.P_MID+" integer, "+
            Medbay.Presc.P_NAME+" varchar(30), "+
            Medbay.Presc.P_DOS+" varchar(10), "+
            Medbay.Presc.P_DAY+" integer, " +
            "PRIMARY KEY ("+ Medbay.Presc.P_PID+")," +
            "FOREIGN KEY ("+ Medbay.Presc.P_PID+") REFERENCES "+ Medbay.Patient.P_TABLE +"(id) on delete cascade on update cascade, " +
            "FOREIGN KEY ("+ Medbay.Presc.P_MID+") REFERENCES "+ Medbay.Medicine.M_TABLE +"(mid) on delete cascade on update cascade )";

    public DBHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(patient);
        db.execSQL(special);
        db.execSQL(doctor);

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

    public int addSpec(String special) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Medbay.Special.S_NAME,special);
        long lid = db.insert(Medbay.Special.S_TABLE,null,values);
        int id = (int) lid;
        return id;
    }

    public boolean addDoc(String name, String email, String pass, String phone, int id) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Medbay.Doctor.D_NAME, name);
        values.put(Medbay.Doctor.D_EMAIL,email);
        values.put(Medbay.Doctor.D_PASSWORD,pass);
        values.put(Medbay.Doctor.D_PHONE,phone);
        values.put(Medbay.Doctor.DS_ID, id);

        long in = db.insert(Medbay.Doctor.D_TABLE, null, values);
        if(in > 0)
            return true;
        else
            return false;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(appoint);
        db.execSQL(med);
        db.execSQL(presc);
    }

    public Cursor viewDoc() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from "+ Medbay.Special.S_TABLE,null);
        return c;
    }
}
