package com.example.medbay.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.medbay.Adapter.DocPatientModel;
import com.example.medbay.AppointModel;
import com.example.medbay.Doctor1;
import com.example.medbay.Doctor2;
import com.example.medbay.PatientMod;
import com.example.medbay.PrescModel;

import java.util.ArrayList;
import java.util.Calendar;


public class DBHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "MEDBAY";
    public static int DB_VERSION = 1;

    public static final String patient = "create table if not exists "+ Medbay.Patient.P_TABLE+
            "(id integer PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            Medbay.Patient.P_NAME+" varchar(30), "+
            Medbay.Patient.P_EMAIL+" varchar(30), "+
            Medbay.Patient.P_DOB+" date, "+
            Medbay.Patient.P_PASSWORD+" varchar(30), "+
            Medbay.Patient.P_PHONE+" varchar(30) )";


    public static final String special = "create table if not exists "+ Medbay.Special.S_TABLE+
            "(sid integer PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            Medbay.Special.S_NAME+" varchar(30) UNIQUE)";


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
            Medbay.Medicine.M_NAME+" varchar(30) UNIQUE)";

    public static final String presc = "create table if not exists "+ Medbay.Presc.P_TABLE +
            "( "+Medbay.Presc.P_PID+" integer, "+
            Medbay.Presc.P_MID+" integer, "+
            Medbay.Presc.P_NAME+" varchar(30), "+
            Medbay.Presc.P_DOS+" varchar(10), "+
            Medbay.Presc.P_DAY+" integer, " +
            "FOREIGN KEY ("+ Medbay.Presc.P_PID+") REFERENCES "+ Medbay.Patient.P_TABLE +"(id) on delete cascade on update cascade, " +
            "FOREIGN KEY ("+ Medbay.Presc.P_MID+") REFERENCES "+ Medbay.Medicine.M_TABLE +"(mid) on delete cascade on update cascade )";

    public DBHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(patient);
        db.execSQL(doctor);
        db.execSQL(appoint);
        db.execSQL(med);
        db.execSQL(presc);
        db.execSQL(special);
        db.execSQL(med);

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
        SQLiteDatabase d = getReadableDatabase();
        int id;

        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("select * from "+ Medbay.Special.S_TABLE+" where "+ Medbay.Special.S_NAME+"='"+special+"'",null);
        if(cursor.getCount() > 0 ) {
            cursor.moveToNext();
            id = cursor.getInt(0);
        }else {
            values.put(Medbay.Special.S_NAME,special);
            long lid = db.insert(Medbay.Special.S_TABLE,null,values);
            id = (int) lid;
        }

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

    public boolean addAppoint(String bk_date, String bk_time, int pid, int did) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Medbay.Appoint.A_PID,pid);
        values.put(Medbay.Appoint.A_DID,did);
        values.put(Medbay.Appoint.A_DATE,bk_date);
        values.put(Medbay.Appoint.A_TIME,bk_time);

        long in = db.insert(Medbay.Appoint.A_TABLE,null,values);

        if(in > 0) {
            return true;
        }else {
            return false;
        }
    }
    public int addMed(String med) {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase d = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        int id;
        Cursor cursor = d.rawQuery("select mid from "+ Medbay.Medicine.M_TABLE+" where "+ Medbay.Medicine.M_NAME+"='"+med+"'",null);
        if (cursor.getCount() >0) {
            cursor.moveToNext();
            id = cursor.getInt(0);
        } else {
            values.put(Medbay.Medicine.M_NAME, med);
            long mid = db.insert(Medbay.Medicine.M_TABLE,null,values);
            id = (int) mid;
        }
        return id;
        
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("delete from "+ Medbay.Doctor.D_TABLE);
        db.execSQL("delete from "+Medbay.Patient.P_TABLE);
        db.execSQL("delete from "+ Medbay.Special.S_TABLE);
        db.execSQL("delete from "+ Medbay.Presc.P_TABLE);
        db.execSQL("delete from "+ Medbay.Medicine.M_TABLE);
        db.execSQL("delete from "+ Medbay.Appoint.A_TABLE);
    }

    public ArrayList<Doctor1> getAllData() {
        ArrayList<Doctor1> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Medbay.Special.S_TABLE,null);

        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String spec_name = cursor.getString(1);
            Doctor1 dc = new Doctor1(id, spec_name);

            arrayList.add(dc);
        }
        return arrayList;
    }

    public ArrayList<Doctor2> getDocData(int id) {
        ArrayList<Doctor2> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Medbay.Doctor.D_TABLE+" where "+ Medbay.Doctor.DS_ID+"="+id,null);
        Cursor c = db.rawQuery("select * from "+Medbay.Special.S_TABLE+" where sid="+id,null);
        c.moveToNext();
        while(cursor.moveToNext()) {
            int did = cursor.getInt(0);
            String doc_name = cursor.getString(1);
            String specialize = c.getString(1);

            Doctor2 dc = new Doctor2(did, doc_name, specialize);

            arrayList.add(dc);
        }
        return arrayList;
    }
    public ArrayList<PatientMod> getPatData(int did) {
        ArrayList<PatientMod> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Medbay.Appoint.A_TABLE+" where "+ Medbay.Appoint.A_DID+"="+did+" ORDER BY "+Medbay.Appoint.A_DATE+" and "+Medbay.Appoint.A_TIME, null);
        while(cursor.moveToNext()) {
            int p_id = cursor.getInt(0);
            Cursor c = db.rawQuery("Select * from "+Medbay.Patient.P_TABLE+" where id="+p_id,null);
            c.moveToNext();
            String pat_name = c.getString(1);
            String app_time = cursor.getString(3);

            PatientMod pd = new PatientMod(p_id,pat_name,app_time);

            arrayList.add(pd);
        }
        return arrayList;
    }


    public ArrayList<DocPatientModel> getPatPresc(int pid) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DocPatientModel> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+ Medbay.Presc.P_TABLE+" where "+ Medbay.Presc.P_PID+"="+pid,null);
        while(cursor.moveToNext()) {
            String name = cursor.getString(2);
            String dosage = cursor.getString(3);
            int days = cursor.getInt(4);
            DocPatientModel mod = new DocPatientModel(name,dosage,days);
            arrayList.add(mod);

        }
        return arrayList;
    }
    public ArrayList<AppointModel> getAppData(int pid) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<AppointModel> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+ Medbay.Appoint.A_TABLE+" where "+ Medbay.Appoint.A_PID+"="+pid,null);
        while(cursor.moveToNext()) {
            int did = cursor.getInt(1);
            String name = getDocName(did);
            String date = cursor.getString(2);
            String time = cursor.getString(3);
            AppointModel mod = new AppointModel(name, date, time);
            arrayList.add(mod);
        }
        return arrayList;
    }

    private String getDocName(int did) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from "+ Medbay.Doctor.D_TABLE+" where did="+did,null);
        c.moveToNext();
        String name = c.getString(1);
        return name;
    }


    public boolean addPresc(int pid, int mid, String medi, String dosa,int d) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Medbay.Presc.P_PID, pid);
        values.put(Medbay.Presc.P_MID, mid);
        values.put(Medbay.Presc.P_NAME,medi);
        values.put(Medbay.Presc.P_DOS, dosa);
        values.put(Medbay.Presc.P_DAY, d);
        long in = db.insert(Medbay.Presc.P_TABLE,null, values);
        if (in > 0) {
            return true;
        } else
            return false;
    }

    public int getPatId(String mail) {
        int id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ Medbay.Patient.P_TABLE+" where "+ Medbay.Patient.P_EMAIL+"='"+mail+"'",null);
        if(cursor.getCount() > 0 ){
            cursor.moveToNext();
            id = cursor.getInt(0);
            return id;
        }
        return 0;
    }


    public ArrayList<PrescModel> getPresc(int pid) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<PrescModel> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+ Medbay.Presc.P_TABLE+" where "+ Medbay.Presc.P_PID+"="+pid,null);
        while(cursor.moveToNext()) {
            String name = cursor.getString(2);
            String dosage = cursor.getString(3);
            int days = cursor.getInt(4);
            PrescModel mod = new PrescModel(name,dosage,days);
            arrayList.add(mod);

        }
        return arrayList;
    }

//    public int getDocId(String uname) {
//        int id;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from "+ Medbay.Doctor.D_TABLE+" where "+Medbay.Doctor.D_NAME+"="+uname,null);
//        if(cursor.getCount() > 0 ){
//            cursor.moveToNext();
//            id = cursor.getInt(0);
//            return id;
//        }
//        return 0;
//    }
}
