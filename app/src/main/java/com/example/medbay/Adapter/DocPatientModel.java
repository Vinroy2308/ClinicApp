package com.example.medbay.Adapter;

public class DocPatientModel {
    String medicine;
    String dosage;
    int days;

    public DocPatientModel(String medicine, String dosage, int days) {
        this.medicine = medicine;
        this.dosage = dosage;
        this.days = days;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getDosage() {
        return dosage;
    }

    public String getDays() {
        return days+"";
    }
}
