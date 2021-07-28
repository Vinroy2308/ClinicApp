package com.example.medbay;

public class PatientMod {
    int id;
    String pat_name;
    String appointment_time;

    public PatientMod(int id,String pat_name, String appointment_time) {
        this.id = id;
        this.pat_name = pat_name;
        this.appointment_time = appointment_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPat_name() {
        return pat_name;
    }

    public void setPat_name(String pat_name) {
        this.pat_name = pat_name;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }
}
